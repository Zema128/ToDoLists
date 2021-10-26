package by.ita.je.service.api;

import by.ita.je.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User create(Long id);

    User readById(Long id);

    Set<User> readFriendsList(Long id);

    void update(Set<User> user, Long id);

    List<User> readAll();
}