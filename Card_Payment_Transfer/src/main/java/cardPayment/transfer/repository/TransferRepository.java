package cardPayment.transfer.repository;

import cardPayment.transfer.model.Transfer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends ReactiveCrudRepository<Transfer,Long> {
}
