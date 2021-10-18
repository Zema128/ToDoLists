package by.ita.je.controller;

import by.ita.je.dto.ToDoDto;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.ToDoService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ToDoController {

    private final ObjectMapper objectMapper;
    private final ToDoService toDoService;

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        toDoService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ToDoDto update(@RequestBody ToDoDto toDoDto, @PathVariable("id") Long id){
        ToDo toDo = objectMapper.convertValue(toDoDto, ToDo.class);
        ToDo toDoUpdated = toDoService.update(toDo, id);
        return objectMapper.convertValue(toDoUpdated, ToDoDto.class);
    }

    @GetMapping("/todo/{id}")
    public ToDoDto findById(@PathVariable("id") Long id){
        return objectMapper.convertValue(toDoService.readById(id), ToDoDto.class);
    }
}
