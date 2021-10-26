package by.ita.je.service.api;

import by.ita.je.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User create(Long id);

    User readById(Long id);

    Set<Long> readFriendsList(Long id);

    void deleteFriend(Long targetId, Long userId);

    void update(Set<User> user, Long id);

    List<User> readAll();
}