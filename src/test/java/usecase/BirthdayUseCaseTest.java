package usecase;

import domain.User;
import infrastructure.CommunicationsService;
import infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Clocker;

import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

class BirthdayUseCaseTest {

    public static final LocalDate TODAY = LocalDate.of(2012, 1, 1);
    public static final LocalDate ANOTHER_TODAY = LocalDate.of(2012, 2, 1);
    public static final User A_USER = new User("name surname", TODAY, "email@email.it");

    CommunicationsService communicationsService = mock(CommunicationsService.class);

    UserRepository userRepository = mock(UserRepository.class);

    Clocker clocker = mock(Clocker.class);

    BirthdayUseCase birthdayUseCase;

    @BeforeEach
    void setUp() {
        birthdayUseCase = new BirthdayUseCase(userRepository, communicationsService, clocker);
    }

    @Test
    void communicationNotSent() {
        when(userRepository.findAll()).thenReturn(singletonList(A_USER));
        when(clocker.today()).thenReturn(ANOTHER_TODAY);
        doNothing().when(communicationsService).sendGreetingsTo(A_USER);

        birthdayUseCase.check();

        verify(userRepository).findAll();
        verify(clocker).today();
        verify(communicationsService, never()).sendGreetingsTo(any());

    }


    @Test
    void communicationSent() {
        when(userRepository.findAll()).thenReturn(singletonList(A_USER));
        when(clocker.today()).thenReturn(TODAY);
        doNothing().when(communicationsService).sendGreetingsTo(A_USER);

        birthdayUseCase.check();

        verify(userRepository).findAll();
        verify(clocker).today();
        verify(communicationsService).sendGreetingsTo(A_USER);

    }


}