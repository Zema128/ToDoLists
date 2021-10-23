package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ObjectMapper objectMapper;
    private final UserService userService;

    @GetMapping("/user/{id}")
    private void create(@PathVariable("id") Long id){
        User userCreated = userService.create(id);
    }
}
