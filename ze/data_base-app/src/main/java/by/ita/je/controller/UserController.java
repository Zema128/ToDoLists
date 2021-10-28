package by.ita.je.controller;

import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/user/{id}")
    public void create(@PathVariable("id") Long id){
        userService.create(id);
    }

    @GetMapping("/friends/{id}")
    public Set<Long> findById(@PathVariable("id") Long id){
        Set<Long> userSet = userService.readFriendsList(id);
        return userSet;
    }

    @GetMapping("/users")
    public List<User> userList(){
        return userService.readAll();
    }
}
