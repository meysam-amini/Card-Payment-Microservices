package cardPayment.transfer.repository;

import cardPayment.transfer.model.Card;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CardRepository extends ReactiveCrudRepository<Card, Long> {

    Mono<Card> findCardByCardnumber(String cardNumber);

}
