package cardPayment.addCard.controller;

import cardPayment.addCard.model.Card;
import cardPayment.addCard.model.CardDto;
import cardPayment.addCard.service.addCard.AddCardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/card-services/add")
@RequiredArgsConstructor
public class AddCardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddCardController.class);

    private final AddCardService addCardService;
    private final ModelMapper modelMapper;



    @PostMapping
    public Mono<ResponseEntity<CardDto>> create(@Valid @RequestBody Card card) {

        LOGGER.info("creating card: {}", card);
        return  addCardService.save(card)
                .map(saved_card-> modelMapper.map(saved_card,CardDto.class))
                .map(saved_card_dto-> ResponseEntity.status(HttpStatus.CREATED)
                        .body(saved_card_dto));
    }

}
