package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setTimeCreated(ZonedDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
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

    @Override
    public void deleteAllById(List<Long> list) {
        userDao.deleteAllById(list);
    }

    @Override
    public User update(User user, Long id) {
        User userUpd = userDao.findById(id).orElseThrow(() -> new RuntimeException("UPDATE"));
        userUpd.setUsername(user.getUsername());
        return userDao.save(userUpd);
    }
}