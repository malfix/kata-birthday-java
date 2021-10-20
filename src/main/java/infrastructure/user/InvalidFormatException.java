package infrastructure.user;

public class InvalidFormatException extends RuntimeException{
    public InvalidFormatException(String message, Exception e) {
        super(message, e);
    }
}
