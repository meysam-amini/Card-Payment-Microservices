package cardPayment.transfer;

import cardPayment.transfer.model.Card;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CardPaymentTransferApplication {


    public static void main(String[] args) {
        SpringApplication.run(CardPaymentTransferApplication.class, args);
    }


    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
