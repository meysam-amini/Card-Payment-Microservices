package cardPayment.transfer.service;


import cardPayment.transfer.model.Transfer;
import cardPayment.transfer.service.providers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class TransferProviderService {

    private final FirstTransferProviderServiceImpl firstTransferProviderService;
    private final SecondTransferProviderServiceImpl secondTransferProviderService;

    public Mono<ResponseEntity<String>> performTransfer(Transfer transfer) {

        String card1 = transfer.getFirstcard();

        if (card1.trim().startsWith("6037")) {
            return firstTransferProviderService.transfer(transfer);
        } else {
            return secondTransferProviderService.transfer(transfer);
        }
    }


}
