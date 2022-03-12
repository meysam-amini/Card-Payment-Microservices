//package notificationService.service;
//
//import com.rabbitmq.client.AMQP;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.rabbitmq.*;
//
//import java.time.Duration;
//import java.util.logging.Level;
//
//
//@Service
//@RequiredArgsConstructor
//public class MessageServiceImpl implements MessageService {
//
//    private final Receiver receiver;
//    private final Sender sender;
//
//    private final String topicName = "transfer-notification";
//
//
//    @Override
//    public Flux<String> receive(String phonenumber, Duration timeout, Integer maxMessageCount) {
//        final String routingKey = topicName + "-" + phonenumber;
//
//
//        final Mono<AMQP.Queue.DeclareOk> declareQueue = sender
//                .declareQueue(QueueSpecification.queue().name(topicName).durable(true))
//                .log("declare-queue", Level.FINER);
//
//        final Mono<String> bindQueue = declareQueue
//                .flatMap(declareOk ->
//                        sender.bindQueue(
//                                BindingSpecification.binding()
//                                        .queue(declareOk.getQueue())
//                                        .exchange(topicName)
//                                        .routingKey(routingKey)
//                        ).map(bindOk -> declareOk.getQueue()))
//                // this code is for returning queueName instead of bind result
//                .log("bind-queue", Level.FINER);
//
//        Flux<String> result = bindQueue
//                .flatMapMany(receiver::consumeAutoAck)
//                .map(item -> new String(item.getBody()));
//
//        if (timeout != null) {
//            result = result.timeout(timeout);
//        }
//
//        if (maxMessageCount != null) {
//            result = result.limitRequest(maxMessageCount);
//        }
//
//        return result;
//    }
//}
