package cardPayment.transfer.controller;

import cardPayment.transfer.model.Transfer;
import cardPayment.transfer.service.TransferProviderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferController.class);

    private final TransferProviderService transferProviderService;

    @PostMapping
    public Mono<ResponseEntity<String>> Transfer(@RequestBody Transfer transfer){

        LOGGER.info("TransferObj: "+transfer.toString());

        return transferProviderService.performTransfer(transfer);
    }

}
