package cardPayment.getCards.service;

import cardPayment.getCards.model.Card;
import cardPayment.getCards.repository.GetCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class GetCardsServiceImpl implements GetCardsService {

    private final GetCardsRepository getCardsRepository;

    @Override
    public Flux<Card> findAll() {
        return getCardsRepository.findAll();
    }
}
