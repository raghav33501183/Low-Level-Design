package customException;

public class ValidationException extends Exception {
    public ValidationException(String errMsg) {
        super(errMsg);
    }
}
