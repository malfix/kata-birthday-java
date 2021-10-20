package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    public static final String VALID_EMAIL = "valid@emai.it";

    @Test
    void birthday() {
        LocalDate aDate = LocalDate.of(2022, 1, 1);
        User user = new User("", aDate, VALID_EMAIL);

        assertTrue(user.isBirthday(aDate));
    }

    @Test
    void notBirthday() {
        LocalDate aDate = LocalDate.of(2022, 1, 1);
        LocalDate anotherDate = LocalDate.of(2022, 2, 1);
        User user = new User("", aDate, VALID_EMAIL);

        assertFalse(user.isBirthday(anotherDate));
    }

    @Test
    void birthdayInALeapYear() {
        LocalDate aDate = LocalDate.of(2020, 2, 29);
        User user = new User("", aDate, VALID_EMAIL);

        assertTrue(user.isBirthday(aDate));
    }

    @Test
    void notBirthdayInALeapYear() {
        LocalDate aDate = LocalDate.of(2020, 2, 29);
        LocalDate anotherDate = LocalDate.of(2020, 2, 28);
        User user = new User("", aDate, VALID_EMAIL);

        assertFalse(user.isBirthday(anotherDate));
    }

    @Test
    void birthdayInNotALeapYear() {
        LocalDate aDate = LocalDate.of(2020, 2, 29);
        LocalDate anotherDate = LocalDate.of(2021, 2, 28);
        User user = new User("", aDate, VALID_EMAIL);

        assertTrue(user.isBirthday(anotherDate));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@supervalid.it", "valid.with.points@supervalid.it", "validwith2numbers@supervalid.it"})
    void checkValidEmails(String email) {
        new User("", LocalDate.now(), email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"4errowhenstartwithnumber@supervalid.it","with spaces@supervalid.it", "withspecialcharså@supervalid.it", "domainnotsupported@supervalid.it.it"})
    void checkNotValidEmails(String email) {
        assertThrows(InvalidMailException.class, () -> new User("", LocalDate.now(), email));
    }
}