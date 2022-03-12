package cardPayment.deleteCard.repository;

import cardPayment.deleteCard.model.Card;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteCardRepository extends ReactiveCrudRepository<Card, Long> {
}
