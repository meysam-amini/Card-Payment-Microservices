package cardPayment.deleteCard.controller;

import cardPayment.deleteCard.service.DeleteCardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/card-services/delete")
@RequiredArgsConstructor
public class DeleteCardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCardController.class);

    private final DeleteCardService deleteCardService;
    private final ModelMapper modelMapper;


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {

        LOGGER.info("deleting card with id; {}", id);

        return  deleteCardService.deleteCard(id)
                .then(Mono.just(ResponseEntity.status(HttpStatus.OK)
                         .body("deleted: " + id)));
    }

}
