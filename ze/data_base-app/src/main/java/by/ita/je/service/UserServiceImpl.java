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
import java.util.Set;

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
    public Set<User> readFriendsList(Long id){
        User user = userDao.findById(id).get();
        Set<User> users = user.getFriends();
        return users;
    }

    @Override
    public void update(Set<User> user, Long id){
        User userUpd = userDao.findById(id).get();
        userUpd.setFriends(user);
        userDao.save(userUpd);
    }

    @Override
    public List<User> readAll(){
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }
}