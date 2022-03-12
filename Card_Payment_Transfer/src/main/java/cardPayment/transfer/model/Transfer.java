package cardPayment.transfer.model;

import cardPayment.transfer.enums.TransferStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table
@EqualsAndHashCode(callSuper = true)
public class Transfer extends Entity {



    private String firstcard;

    private String secondcard;

    private String secondcardphonenumber;

    private long amount;

    private LocalDate Date;

    private TransferStatus status;


}
