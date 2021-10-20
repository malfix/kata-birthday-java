package infrastructure.user;

import domain.User;
import infrastructure.UserRepository;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FileUserRepository implements UserRepository {
    private final File file;

    public FileUserRepository(File file) {
        this.file = file;
    }

    @Override
    public List<User> findAll() {
        try {
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            lines.remove(0);
            return lines.stream().map(s -> {
                String[] split = s.split(",");
                return new User(
                        split[0],
                        LocalDate.parse(split[1], DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        split[2]
                );
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            throw new InvalidFormatException("Invalid file", e);
        }
    }
}
