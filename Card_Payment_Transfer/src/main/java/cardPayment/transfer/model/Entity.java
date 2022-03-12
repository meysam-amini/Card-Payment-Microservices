package cardPayment.transfer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@Data
public class Entity {
    @Id
    private long id;
    private long version;
}
