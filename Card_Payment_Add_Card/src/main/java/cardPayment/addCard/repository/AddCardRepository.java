package cardPayment.addCard.repository;

import cardPayment.addCard.model.Card;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddCardRepository extends ReactiveCrudRepository<Card, Long> {
}
