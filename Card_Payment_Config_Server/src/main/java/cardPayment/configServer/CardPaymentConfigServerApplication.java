package cardPayment.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CardPaymentConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardPaymentConfigServerApplication.class, args);
    }

}
