package by.ita.je.controller;

import by.ita.je.config.ClientConfig;
import by.ita.je.dto.ToDoDto;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
public class ToDoController {

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final ClientConfig clientConfig;

    private Long getUserId(){
        String id = String.valueOf(userService.getCurrentUserId());
        Long userId = Long.parseLong(id.replaceAll("[^0-9]", ""));
        return userId;
    }

    @GetMapping("/")
    public String home(Model model){
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.getForEntity(clientConfig.getUrl() + "/todos/" + getUserId(), ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("todos", list);
        return "home";
    }

    @GetMapping("/createtodo")
    public String createToDo(Model model){
        model.addAttribute("todo", new ToDoDto());
        return "FormTodo";
    }

    @PostMapping("/createtodo")
    public String createdToDo(ToDoDto toDoDto){
        restTemplate.postForObject(clientConfig.getUrl() + "/create/" + getUserId(), toDoDto, ToDoDto.class);
        return "redirect:/";
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable(value = "id", required = false) Long id, Model model){
        ToDoDto toDoDto= restTemplate.getForObject(clientConfig.getUrl() + "/todo/" + id, ToDoDto.class);
        model.addAttribute("todo", toDoDto);
        return "FormUpdate";
    }

    @PostMapping(value = "/update")
    public String updated(ToDoDto toDoDto){
        restTemplate.put(clientConfig.getUrl() + "/update/" + toDoDto.getId(), toDoDto, ToDoDto.class);
        return "redirect:/";
    }

    @GetMapping(value = "/deletetodo/{id}")
    public String deleteById(@PathVariable(value = "id",required = false) Long id){
        restTemplate.delete(clientConfig.getUrl() + "/delete/" + id);
        return "redirect:/";
    }

    @GetMapping("/sharedlist")
    public String sharedList(){
        return "SharedList";
    }
}