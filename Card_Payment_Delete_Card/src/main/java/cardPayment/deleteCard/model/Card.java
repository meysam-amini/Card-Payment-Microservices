package cardPayment.deleteCard.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table
public class Card {

    @Id
    private Long id;

    private String fullname;

    private String cardnumber;

    private int cvv2;

    private int password1;

    private int password2;

    private String expiredate;

    private Long balance;

    private int version;



}
