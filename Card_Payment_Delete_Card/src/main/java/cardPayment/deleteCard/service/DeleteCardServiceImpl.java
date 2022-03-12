package cardPayment.deleteCard.service;

import cardPayment.deleteCard.repository.DeleteCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class DeleteCardServiceImpl implements DeleteCardService {

    private final DeleteCardRepository deleteCardRepository;

    @Override
    public Mono<Void> deleteCard(Long id) {
        return deleteCardRepository.deleteById(id);
    }
}
