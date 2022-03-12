package cardPayment.transfer.exceptions;

public class OptimisticLockException extends RuntimeException{

    public OptimisticLockException(String msg){
        super(msg);
    }
}
