package by.ita.je.controller;

import by.ita.je.dto.SubTaskDto;
import by.ita.je.model.SubTask;
import by.ita.je.service.api.SubTaskService;
import by.ita.je.service.api.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubTaskController {

    private final ObjectMapper objectMapper;
    private final SubTaskService subTaskService;



}