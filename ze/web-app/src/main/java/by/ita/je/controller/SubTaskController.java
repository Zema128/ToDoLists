package by.ita.je.controller;

import by.ita.je.config.ClientConfig;
import by.ita.je.dto.SubTaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class SubTaskController {

    private final RestTemplate restTemplate;
    private Long toDoId;
    private final ClientConfig clientConfig;

    public SubTaskController(RestTemplate restTemplate, ClientConfig clientConfig) {
        this.restTemplate = restTemplate;
        this.clientConfig = clientConfig;
    }

    @GetMapping("/subtasks/{id}")
    public String subtasks(@PathVariable(value = "id",required = false) Long id, Model model){
        toDoId = id;
        ResponseEntity<SubTaskDto[]> responseEntity =
                restTemplate.getForEntity(clientConfig.getUrl() + "/subtasks/" + id, SubTaskDto[].class);
        List<SubTaskDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("subtasks", list);
        return "subtasks";
    }

    @GetMapping("/createsubtask")
    public String createToDo(Model model){
        model.addAttribute("subtask", new SubTaskDto());
        return "FormSubtask";
    }

    @PostMapping("/createsubtask")
    public String createdToDo(SubTaskDto subTaskDto){
        restTemplate.postForObject(clientConfig.getUrl() + "/createsubtask/" + toDoId, subTaskDto, SubTaskDto.class);
        return "redirect:/subtasks/" + toDoId;
    }

    @GetMapping(value = "/updatesubtask/{id}")
    public String update(@PathVariable(value = "id", required = false) Long id, Model model){
        SubTaskDto subTaskDto= restTemplate.getForObject(clientConfig.getUrl() + "/subtask/" + id, SubTaskDto.class);
        model.addAttribute("subtask", subTaskDto);
        return "FormUpdateSubtask";
    }

    @PostMapping(value = "/updatesubtask")
    public String updated(SubTaskDto subTaskDto){
        restTemplate.put(clientConfig.getUrl() + "/updatesubtask/" + subTaskDto.getId(), subTaskDto, SubTaskDto.class);
        return "redirect:/subtasks/" + toDoId;
    }

    @GetMapping(value = "/deletesubtask/{id}")
    public String deleteById(@PathVariable(value = "id",required = false) Long id){
        restTemplate.delete(clientConfig.getUrl() + "/deletesubtask/" + id);
        return "redirect:/subtasks/" + toDoId;
    }
}