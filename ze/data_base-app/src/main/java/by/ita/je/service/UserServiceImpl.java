package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
    public Set<Long> readFriendsList(Long id){
        User user = userDao.findById(id).get();
        Set<User> users = user.getFriends();
        Set<Long> userList = new HashSet<>();
        for (User us : users) {
            userList.add(us.getId());
        }
        return userList;
    }

    @Override
    public void deleteFriend(Long targetId, Long userId){
        User user = readById(userId);
        Set<User> userSet = user.getFriends();
        userSet.removeIf(user1 -> user1.getId() == targetId);
        update(userSet, userId);
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