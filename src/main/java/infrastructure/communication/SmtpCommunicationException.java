package infrastructure.communication;

public class SmtpCommunicationException extends RuntimeException {
    public SmtpCommunicationException(Exception e) {
        super(e);
    }
}
