package by.ita.je.controller;

import by.ita.je.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    private void create(@PathVariable("id") Long id){
        userService.create(id);
    }
}
