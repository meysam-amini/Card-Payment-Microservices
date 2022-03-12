package cardPayment.deleteCard.service;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DeleteCardService {

    /**
     * <p>passes an object's id to
     * {@link ReactiveCrudRepository#deleteById(java.lang.Object)}
     * to removing object from database </p>
     * @param id of type Long
     * @return Mono of type Void
     *
     * */
    Mono<Void> deleteCard(Long id);
}
