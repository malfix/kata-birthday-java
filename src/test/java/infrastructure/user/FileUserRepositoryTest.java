package infrastructure.user;

import domain.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FileUserRepositoryTest {
    @Test
    void validFile() {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("valid.csv")).getFile());
        FileUserRepository fileUserRepository = new FileUserRepository(file);
        List<User> expected = asList(
                new User("ciccio pasticcio", LocalDate.of(2000, 12, 31), "cicciopasticcio@gmail.com"),
                new User("mario bianchi", LocalDate.of(1999, 1, 11), "mariobianchi@gmail.com")
        );

        List<User> actual = fileUserRepository.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void emptyFile() {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("empty.csv")).getFile());
        FileUserRepository fileUserRepository = new FileUserRepository(file);

        List<User> actual = fileUserRepository.findAll();

        assertEquals(Collections.<User>emptyList(), actual);
    }

    @Test()
    void invalidFile() {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("invalid.csv")).getFile());
        FileUserRepository fileUserRepository = new FileUserRepository(file);

        assertThrows(InvalidFormatException.class, fileUserRepository::findAll);
    }
}