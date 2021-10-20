package by.ita.je.controller;

import by.ita.je.dto.SubTaskDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BusinessController {

    private final ObjectMapper objectMapper;
    private final BusinessService businessService;

    @PostMapping("/create/{id}")
    public ToDoDto create(@RequestBody ToDoDto toDoDto,@PathVariable("id") Long userId){
        ToDo toDo = objectMapper.convertValue(toDoDto, ToDo.class);
        return objectMapper.convertValue(businessService.create(toDo, userId), ToDoDto.class);
    }

    @GetMapping("/todos/{id}")
    public List<ToDoDto> readAll(@PathVariable("id") Long userId){
        List<ToDo> todos = businessService.readAll(userId);
        List<ToDoDto> list = new ArrayList<>();
        for (ToDo toDo : todos) {
            list.add(objectMapper.convertValue(toDo, ToDoDto.class));
        }
        return list;
    }



}