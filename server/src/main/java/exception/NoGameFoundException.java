package exception;

public class NoGameFoundException extends RuntimeException {
    public NoGameFoundException(String message) {
        super(message);
    }
}
