package cardPayment.transfer.myInterface;

import cardPayment.transfer.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveTestService {

    private final CardRepository cardRepository;

    public String call(milad milad){
        ////
        return milad.onReceive(cardRepository);
    }
}


@RequiredArgsConstructor
class Caller{

    private final SaveTestService saveTestService;

    private void test(){

        saveTestService.call(new milad() {
            @Override
            public String onReceive(Object object) {
                System.out.println(object);
                //UI
                return null;
            }
        });
    }

}