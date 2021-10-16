package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ObjectMapper objectMapper;
    private final UserService userService;

    @PostMapping("/user")
    private UserDto create(@RequestBody UserDto userDto){
        User user = objectMapper.convertValue(userDto, User.class);
        User userCreated = userService.create(user);
        return objectMapper.convertValue(userCreated, UserDto.class);
    }
}
