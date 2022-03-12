package notificationService.controller;

import lombok.RequiredArgsConstructor;
import notificationService.model.Message;
import notificationService.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.RabbitFlux;

import java.time.Duration;

@RestController
@RequestMapping("/show-messages")
@RequiredArgsConstructor
public class MessageController {

//    private final MessageService messageService;

//    @GetMapping(value = "/{phonenumber}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> receive(@PathVariable String phonenumber,
//                                @RequestParam(required = false) Integer timeout,
//                                @RequestParam(required = false) Integer maxMessageCount) {
//
//        RabbitFlux.createReceiver() // default settings
//                .consumeAutoAck("reactor-rabbit")
//                .map(delivery -> new Message(delivery.getBody().toString())) // delivery.getBody() returns byte array
//                .subscribe(dto -> {
//                    System.out.println("message: "+dto);
//                });
//
//        System.out.println("getting msg: "+phonenumber);
//        return messageService.receive(phonenumber, timeout == null ? null : Duration.ofMillis(timeout), maxMessageCount);
//    }

}
