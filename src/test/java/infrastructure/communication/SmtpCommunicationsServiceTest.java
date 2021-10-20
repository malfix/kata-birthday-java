package infrastructure.communication;

import domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SmtpCommunicationsServiceTest {
    @Test
    void happyPath() {
        SmtpCommunicationsService smtpCommunicationsService = new SmtpCommunicationsService();

        smtpCommunicationsService.sendGreetingsTo(new User("mario bianchi", LocalDate.of(1999, 1, 11), "mariobianchi@gmail.com"));
    }
}