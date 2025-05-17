package exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String errMsg) {
        super(errMsg);
    }
}
