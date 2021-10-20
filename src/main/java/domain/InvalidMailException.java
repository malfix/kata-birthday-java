package domain;

public class InvalidMailException extends RuntimeException{
    public InvalidMailException(String message) {
        super(message);
    }
}
