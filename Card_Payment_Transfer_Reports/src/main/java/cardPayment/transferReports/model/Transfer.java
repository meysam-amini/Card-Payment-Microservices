package cardPayment.transferReports.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table
public class Transfer {

    @Id
    private Long Id;

    private String firstcard;

    private String secondcard;

    private String secondcardphonenumber;

    private Long amount;

    private LocalDate Date;

    private String status;
}
