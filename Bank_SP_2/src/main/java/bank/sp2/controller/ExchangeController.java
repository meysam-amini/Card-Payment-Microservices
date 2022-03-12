package bank.sp2.controller;

import bank.sp2.model.TransferDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sp2/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeController.class);

    private final Environment environment;


    @PostMapping
    public Mono<Boolean> exchange(@RequestBody TransferDto transferDto) {

        boolean response= Boolean.parseBoolean(environment.getProperty("sp2.exchange.response"));

        LOGGER.info("SP2 Response : "+response);
        LOGGER.info("Transfer Object : "+transferDto);

        if (response)
            return Mono.just(true);
        else
            return Mono.just(false);
    }
}
