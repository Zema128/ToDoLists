package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<UserDto> findById(@PathVariable("id") Long id){
        Set<User> userSet = userService.readFriendsList(id);
        List<UserDto> list = new ArrayList<>();
        for (User user : userSet) {
            list.add(objectMapper.convertValue(user, UserDto.class));
        }
        return list;
    }

    @GetMapping("/users")
    public List<User> userList(){
        return userService.readAll();
    }
}
