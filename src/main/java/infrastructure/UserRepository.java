package infrastructure;

import domain.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
