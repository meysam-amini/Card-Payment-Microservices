package cardPayment.addCard.service;

import cardPayment.addCard.model.Card;
import reactor.core.publisher.Mono;

public interface BaseService <T>{

    /**
     * <p>passes a {@link Card} object to repository
     * for saving in database </p>
     * @param object of type {@link Card}
     * @return Mono of type {@link Card}
     *
     * */
    Mono<T> save(T object);
}
