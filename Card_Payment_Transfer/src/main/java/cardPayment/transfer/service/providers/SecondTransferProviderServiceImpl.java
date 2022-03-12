package cardPayment.transfer.service.providers;

import cardPayment.transfer.enums.TransferStatus;
import cardPayment.transfer.exceptions.OptimisticLockException;
import cardPayment.transfer.model.Card;
import cardPayment.transfer.model.FirstProviderDto;
import cardPayment.transfer.model.SecondProviderDto;
import cardPayment.transfer.model.Transfer;
import cardPayment.transfer.repository.CardRepository;
import cardPayment.transfer.repository.TransferRepository;
import cardPayment.transfer.service.SaveService;
import cardPayment.transfer.service.rabbitMQService.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class SecondTransferProviderServiceImpl extends AbstractTransfeProviderService<SecondProviderDto> {

    private final ModelMapper modelMapper;
    private final TransferRepository transferRepository;
    private final CardRepository cardsRepository;
    private final RabbitMQService rabbitMQService;
    private final SaveService saveService;


    private final String SP_2_ADDRESS = "http://localhost:8082/sp2/exchange";

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstTransferProviderServiceImpl.class);

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Mono<ResponseEntity<String>> transfer(Transfer transfer) {

        transfer.setDate(LocalDate.now());
        String msg = transfer.getAmount() + " rials from" + transfer.getFirstcard();

        return checkFirstCardBalance(transfer).publishOn(Schedulers.boundedElastic()).flatMap(first_card_balance_ok ->

        {
            if (first_card_balance_ok) {

                Mono<Boolean> response = callExchangeSP(SP_2_ADDRESS, transfer, null);

                return checkExchangeSPResponse(response)
                        .flatMap(status -> updateTransferStatus(transfer, status)
                                .flatMap(transferRepository::save)
                                .flatMap(saved_transfer -> {

                                    removeFromOnlineWithdrawals(transfer.getFirstcard(),transfer.getAmount());


                                    if (status == TransferStatus.SUCCESSFUL)  {
                                        return updateCardsBalances(transfer).flatMap(both_updates_ok -> {

                                            if (both_updates_ok) {
                                                sendMessage(msg);
                                                return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("Transfer Status: " + status + "!\n" + transfer));
                                            } else {
                                                callBackSP(false);
                                                return Mono.error(new OptimisticLockException("Optimistic Lock Exception!"));
                                            }
                                        });
                                    } else
                                        // Bank Service Provider isn't up, or couldn't perform the transfer(Uncommitted or Failed):
                                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfer Status: " + status + "!\n" + transfer));

                                }));
            }
            // First card doesn't have enough balance or invalid card:
            else
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Enough Balance Or Wrong Card Selected"));
        }).onErrorResume(e -> {
                    // Error on saving transfer data to our database:
                    callBackSP(false);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error"));
                }
        );
    }

    private void callBackSP(boolean b) {
        //call back bank SP
    }



    @Override
    public Mono<Boolean> checkFirstCardBalance(Transfer transfer) {

        return cardsRepository.findCardByCardnumber(transfer.getFirstcard())
                .flatMap(card -> {

                    LOGGER.info("checking balance of card " + card.getCardnumber() + ": " + card.getBalance());

                    return CanBeAddedToConcurrentWithdrawals(card.getCardnumber(),card.getBalance(),transfer.getAmount());
                })
                // We have no card with this number:
                .switchIfEmpty(Mono.just(false));
    }

    @Override
    public Mono<Boolean> callExchangeSP(String URI, Transfer transfer, SecondProviderDto dto) {

        return WebClient.builder()
                .baseUrl(URI)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post().body(BodyInserters.fromValue(transfer)).retrieve()
                .bodyToMono(Boolean.class);

    }


    @Override
    public Mono<Boolean> updateCardsBalances(Transfer transfer) {
        return cardsRepository.findCardByCardnumber(transfer.getFirstcard())
                .flatMap(card_1 -> updateOptimistic(card_1, card_1.getBalance() - transfer.getAmount()))
                .zipWith(cardsRepository.findCardByCardnumber(transfer.getSecondcard())
                        .flatMap(card2 -> updateOptimistic(card2, card2.getBalance() + transfer.getAmount())))
                .map(t -> {
                    LOGGER.info("update responses: " + t.getT1() + " , " + t.getT2());
                    return (t.getT1() && t.getT2());
                });

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendMessage(String message) {
        rabbitMQService.send(message);
    }

    @Override
    public Mono<Boolean> updateOptimistic(Card card, Long newBalance) {
        card.setBalance(newBalance);
        return saveService.saveCardOptimistic(card);
    }

}
