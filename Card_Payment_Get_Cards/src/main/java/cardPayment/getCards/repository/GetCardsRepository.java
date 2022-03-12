package cardPayment.getCards.repository;

import cardPayment.getCards.model.Card;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetCardsRepository extends ReactiveCrudRepository<Card, Long> {
}
