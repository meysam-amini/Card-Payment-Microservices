package notificationService.service;


import reactor.core.publisher.Flux;

import java.time.Duration;

public interface MessageService {

    Flux<String> receive(String phonenumber, Duration timeout, Integer maxMessageCount);}
