package cardPayment.transferReports;

import io.r2dbc.spi.ConnectionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CardPaymentTransferReportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardPaymentTransferReportsApplication.class, args);
    }

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .namedParameters(true)
                .build();
    }
}
