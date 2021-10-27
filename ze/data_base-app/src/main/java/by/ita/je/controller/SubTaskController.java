package by.ita.je.controller;

import by.ita.je.dto.SubTaskDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.SubTaskService;
import by.ita.je.service.api.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SubTaskController {

    private final ObjectMapper objectMapper;
    private final SubTaskService subTaskService;

    @DeleteMapping("/deletesubtask/{id}")
    public void deleteSubTask(@PathVariable(value = "id",required = false) Long id){
        subTaskService.deleteById(id);
    }

    @GetMapping("/subtask/{id}")
    public SubTaskDto findById(@PathVariable("id") Long id){
        return objectMapper.convertValue(subTaskService.readById(id), SubTaskDto.class);
    }

    @PutMapping("/updatesubtask/{id}")
    public SubTaskDto update(@RequestBody SubTaskDto subTaskDto, @PathVariable("id") Long id){
        SubTask subTask = objectMapper.convertValue(subTaskDto, SubTask.class);
        SubTask subTaskUpdated = subTaskService.update(subTask, id);
        return objectMapper.convertValue(subTaskUpdated, SubTaskDto.class);
    }

    @GetMapping("/subtasks")
    public List<SubTaskDto> subTasks(){
        List<SubTaskDto> subTaskDtos = subTaskService.readAll()
                .stream()
                .map(subTask -> objectMapper.convertValue(subTask, SubTaskDto.class)).collect(Collectors.toList());
        return subTaskDtos;
    }

    @GetMapping("/sentsubtask/{subTaskId}")
    public void sentToDo(@PathVariable("subTaskId") Long subTaskId){
        subTaskService.sentMail(subTaskId);
    }
}