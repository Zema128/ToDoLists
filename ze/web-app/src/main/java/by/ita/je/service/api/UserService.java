package by.ita.je.service.api;

import by.ita.je.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User readByUsername(String username);

    User create(User user);

    User readById(Long id);

    List<User> readAll();

    void deleteById(Long id);

    void deleteAllById(List<Long> list);

    User update(User user, Long id);

    public Optional<String> getCurrentUser();

    public Optional<Long> getCurrentUserId();
}