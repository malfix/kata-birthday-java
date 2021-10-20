package usecase;

import domain.User;
import infrastructure.CommunicationsService;
import infrastructure.UserRepository;
import service.Clocker;

import java.time.LocalDate;
import java.util.List;

public class BirthdayUseCase {
    private final UserRepository userRepository;
    private final CommunicationsService communicationsService;
    private final Clocker clocker;

    public BirthdayUseCase(UserRepository userRepository, CommunicationsService communicationsService, Clocker clocker) {
        this.userRepository = userRepository;
        this.communicationsService = communicationsService;
        this.clocker = clocker;
    }

    public void check() {
        List<User> users = userRepository.findAll();
        LocalDate today = clocker.today();
        users.stream()
                .filter(user -> user.isBirthday(today))
                .forEach(communicationsService::sendGreetingsTo);
    }

}
