package cardPayment.transfer.configurations.CustomOptimisticLock;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OptimisticallyLocked {
}
