package by.ita.je.service.api;

import by.ita.je.model.User;

import java.util.List;

public interface UserService {

    User create(Long id);

    User readById(Long id);

    List<User> readAll();

    void deleteById(Long id);

}