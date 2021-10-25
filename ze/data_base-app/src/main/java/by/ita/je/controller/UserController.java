package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public UserDto findById(@PathVariable("id") Long id){
        return objectMapper.convertValue(userService.readById(id), UserDto.class);
    }
}
