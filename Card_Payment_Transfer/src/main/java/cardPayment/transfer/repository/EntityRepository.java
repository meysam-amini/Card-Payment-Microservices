package cardPayment.transfer.repository;

import cardPayment.transfer.model.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository <T extends Entity> extends ReactiveCrudRepository<T ,Long> {
}
