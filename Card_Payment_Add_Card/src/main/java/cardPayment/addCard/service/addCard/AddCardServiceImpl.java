package cardPayment.addCard.service.addCard;

import cardPayment.addCard.model.Card;
import cardPayment.addCard.repository.AddCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddCardServiceImpl implements AddCardService{

    private final AddCardRepository addCardRepository;

    @Override
    public Mono<Card> save(Card card) {
        return addCardRepository.save(card);
    }
}
