package by.ita.je.controller;

import by.ita.je.dto.ToDoDto;
import by.ita.je.service.api.ToDoService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ToDoController {

    private final ObjectMapper objectMapper;
    private final ToDoService toDoService;

    @PostMapping("/todo")
    public ToDoDto create(@RequestBody ToDoDto toDoDto){
        return null;
    }
}
