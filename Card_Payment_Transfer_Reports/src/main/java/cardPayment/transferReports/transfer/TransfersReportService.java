package cardPayment.transferReports.transfer;

import cardPayment.transferReports.model.TransferReportDto;
import reactor.core.publisher.Flux;

public interface TransfersReportService<T> {
    Flux<TransferReportDto> transfersReports(T object);

}