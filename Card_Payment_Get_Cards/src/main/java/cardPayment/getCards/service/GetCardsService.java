package cardPayment.getCards.service;


import cardPayment.getCards.model.Card;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetCardsService {

    Flux<Card> findAll();
}
