package cardPayment.transfer.controller;

import cardPayment.transfer.model.Card;
import cardPayment.transfer.model.Transfer;
import cardPayment.transfer.repository.CardRepository;
import cardPayment.transfer.repository.TransferRepository;
import cardPayment.transfer.service.SaveService;
import cardPayment.transfer.service.TransferProviderService;
import cardPayment.transfer.service.providers.FirstTransferProviderServiceImpl;
import cardPayment.transfer.service.providers.SecondTransferProviderServiceImpl;
import cardPayment.transfer.service.rabbitMQService.RabbitMQService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebFluxTest
@Import({TransferRepository.class,CardRepository.class,SaveService.class, TransferProviderService.class})
@ExtendWith(SpringExtension.class)
class TransferControllerTest {


    @MockBean
    TransferRepository repository;
    @MockBean
    CardRepository cardRepository;

    @Autowired
    private WebTestClient webClient;


    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    SaveService saveService;

    @MockBean
    private RabbitMQService rabbitMQService;

    @MockBean
    private ModelMapper modelMapper;

    private Transfer transfer;
    private Card card_1;
    private Card card_1_2;
    private Card card_2;
    private Card card_2_2;

    @MockBean
    private FirstTransferProviderServiceImpl firstTransferProviderService;
    @MockBean
    private SecondTransferProviderServiceImpl secondTransferProviderService;


    private final String SP1_URI = "http://localhost:8082/sp1/exchange";
    private final String SP2_URI = "http://localhost:8082/sp2/exchange";


    @BeforeEach
    void setUp() {

        transfer = new Transfer();
//        transfer.setId(1234435345L);
        transfer.setFirstcard("6037 9956 6545 3463");
        transfer.setSecondcard("7037 9956 6545 3463");
        transfer.setAmount(1000L);
        transfer.setDate(LocalDate.now());
        transfer.setSecondcardphonenumber("0939 035 3105");

        card_1 = new Card();
        card_1.setId(1L);
        card_1.setBalance(10000L);
        card_1.setCardnumber("6037 9956 6545 3463");
        card_1.setVersion(1L);

        card_2 = new Card();
        card_2.setId(3L);
        card_2.setBalance(10000L);
        card_2.setCardnumber("7037 9956 6545 3463");
        card_2.setVersion(3L);


        webClient = webClient
                .mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build();

        when(repository.save(any())).thenReturn(Mono.just(transfer));
        when(cardRepository.findCardByCardnumber(any()))
                .thenReturn(Mono.just(card_1))
                .thenReturn(Mono.just(card_2));


    }

    @Test
    void testTransferUncommitted() {

        when(firstTransferProviderService
                .callExchangeSP(any(), any(), any()))
                .thenReturn(Mono.just(false));

        transfer.setFirstcard("6037 9956 8314 4554");


        webClient.post()
                .uri("/transfer")
                .body(BodyInserters.fromValue(transfer))
                .exchange()
                .expectStatus()
                .isNotFound();

    }


    @Test
    void testTransferSaveToDataBaseFailed() {

        when(repository.save(any())).thenThrow(new RuntimeException("test runtime exception"));

        webClient.post()
                .uri("/transfer")
                .body(BodyInserters.fromValue(transfer))
                .exchange()
                .expectStatus()
                .isNotFound().expectBody(String.class).isEqualTo("Server Error");

    }


}