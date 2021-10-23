package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User create(Long id) {
        User newUser = new User();
        newUser.setId(id);
        newUser.setTimeCreated(LocalDateTime.now());
        return userDao.save(newUser);
    }

    @Override
    public User readById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}