package cardPayment.getCards.model;

import lombok.Data;

@Data
public class CardDto {

    private Long id;
    private String fullname;
    private String cardnumber;
}
