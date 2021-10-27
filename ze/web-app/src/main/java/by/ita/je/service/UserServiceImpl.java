package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User readByUsername(String username){
        User user = userDao.findByUsername(username);
        if (user == null) throw new RuntimeException("Нет такого пользователя!");
        return user;
    }

    @Override
    public User create(User user) {
        user.setTimeCreated(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public void changePass(User user, Long id){
        User userChange = readById(id);
        userChange.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(userChange);
    }

    @Override
    public Optional<Long> getCurrentUserId(){
        Optional<String> currentUser = getCurrentUser();
        if (currentUser.isPresent()){
            return Optional.ofNullable(userDao.findByUsername(currentUser.get()))
                    .map(User::getId);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName());
        }
        return Optional.empty();
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