package cardPayment.getCards.controller;

import cardPayment.getCards.model.CardDto;
import cardPayment.getCards.service.GetCardsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/card-services/get-cards")
@RequiredArgsConstructor
public class GetCardsController {

    private final GetCardsService getCardsService;
    private final ModelMapper modelMapper;


    @GetMapping()
    public Flux<CardDto> findAll() {
        return  getCardsService.findAll().map(card -> modelMapper.map(card,CardDto.class));
    }

}
