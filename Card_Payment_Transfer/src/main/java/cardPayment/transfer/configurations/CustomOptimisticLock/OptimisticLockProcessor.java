package cardPayment.transfer.configurations.CustomOptimisticLock;

import cardPayment.transfer.model.Entity;
import cardPayment.transfer.repository.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
@RequiredArgsConstructor
public class OptimisticLockProcessor {

    @Around("@annotation(OptimisticallyLocked)")
    public Object OptimisticallyLocked(ProceedingJoinPoint joinPoint) throws Throwable {

        Entity entity = (Entity) joinPoint.getArgs()[0];
        ReactiveCrudRepository<Entity, Long> repository = (ReactiveCrudRepository) joinPoint.getArgs()[1];

            repository.findById(entity.getId())
                    .map(founded -> {
                        if (founded.getVersion() == entity.getVersion()) {
                            entity.setVersion(entity.getVersion() + 1);
                            try {

                                return joinPoint.proceed();


                            } catch (Throwable e) {
                                return Mono.error(new RuntimeException("Server Error!"));
                            }
                        } else
                            return Mono.error(new RuntimeException("Optimistic Lock Exception!"));
                    }).subscribe();


        return joinPoint.proceed();
    }
}
