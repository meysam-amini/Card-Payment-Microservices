package cardPayment.transferReports.repository;

import cardPayment.transferReports.model.Transfer;
import cardPayment.transferReports.model.TransferReportDto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.time.LocalDate;

@Repository
public interface TransferRepository extends ReactiveCrudRepository<Transfer,Long> {


//    @Query("SELECT status , count(status) from (SELECT * FROM transfer WHERE date BETWEEN ?1 AND ?2) A GROUP BY status")
//    Flux<TransferReportDto> getTransfersStatusCountsBetween(LocalDate dat1, LocalDate date2);
}
