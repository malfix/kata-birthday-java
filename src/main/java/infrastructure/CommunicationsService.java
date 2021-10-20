package infrastructure;

import domain.User;

public interface CommunicationsService {
    void sendGreetingsTo(User aUser);
}
