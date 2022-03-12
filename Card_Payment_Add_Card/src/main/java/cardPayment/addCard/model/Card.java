package cardPayment.addCard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;


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

    private Integer version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardnumber.matches(card.cardnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardnumber);
    }
}
