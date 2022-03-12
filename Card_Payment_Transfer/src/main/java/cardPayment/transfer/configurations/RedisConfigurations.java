package cardPayment.transfer.configurations;

import cardPayment.transfer.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfigurations {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

//    @Bean
//    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
//        return new LettuceConnectionFactory(redisHost, redisPort);
//    }

   /* @Bean
    public ReactiveRedisTemplate<String, Card> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Card> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Card.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Card> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Card> context =
                builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, context);
    }*/


    /*@Bean
    public ReactiveRedisTemplate<String,String> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory){
        return new ReactiveStringRedisTemplate(factory);
    }*/
}
