package by.ita.je.controller;

import by.ita.je.dto.SubTaskDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.ToDoService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/subtasks/{id}")
    public List<SubTaskDto> readAll(@PathVariable("id") Long todoId){
        List<SubTask> subTask = toDoService.readAllSubtasks(todoId);
        List<SubTaskDto> list = new ArrayList<>();
        for (SubTask subTasks : subTask) {
            list.add(objectMapper.convertValue(subTasks, SubTaskDto.class));
        }
        return list;
    }

    @GetMapping("/todos")
    public List<ToDoDto> todos(){
        List<ToDoDto> toDoDtos = toDoService.readAll()
                .stream()
                .map(toDo -> objectMapper.convertValue(toDo, ToDoDto.class)).collect(Collectors.toList());
        return toDoDtos;
    }
}