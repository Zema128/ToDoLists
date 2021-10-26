package by.ita.je.controller;

import by.ita.je.dto.InviteDto;
import by.ita.je.dto.SharedListDto;
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
public class ShareController {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app";
    private final UserService userService;

    private Long getUserId(){
        String id = String.valueOf(userService.getCurrentUserId());
        Long userId = Long.parseLong(id.replaceAll("[^0-9]", ""));
        return userId;
    }

    @GetMapping("/readallshared")
    public String readAllShared(Model model){
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.getForEntity(baseUrl + "/sharedallread/" + getUserId(), ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
//        for (ToDoDto t: list) {
//            t.setUsername(userService.readById(t.getUserDto().getId()).getUsername());
//        }
        model.addAttribute("onlyread", list);
        ResponseEntity<ToDoDto[]> responseEntityChange =
                restTemplate.getForEntity(baseUrl + "/sharedallchange/" + getUserId(), ToDoDto[].class);
        List<ToDoDto> listChange = Arrays.asList(responseEntity.getBody());
//        for (ToDoDto t: list) {
//            t.setUsername(userService.readById(t.getUserDto().getId()).getUsername());
//        }
        model.addAttribute("readchange", listChange);
        return "SharedList";
    }

    @GetMapping("/createshare/{id}")
    public String createShare(@PathVariable("id") Long id, Model model){
        SharedListDto sharedList = new SharedListDto();
        sharedList.setToDoId(id);
        model.addAttribute("shared", sharedList);
        return "SharedToUser";
    }

    @PostMapping("/createshareread/{toDoId}")
    public String createShareRead(@PathVariable("toDoId") Long toDoId, SharedListDto sharedListDto){
        sharedListDto.setFromId(getUserId());
        sharedListDto.setToId(userService.readByUsername(sharedListDto.getUsername()).getId());
        restTemplate.postForObject(baseUrl + "/createShared", sharedListDto, SharedListDto.class);
        return "SuccessfulShared";
    }

    @PostMapping("/createsharechange/{toDoId}")
    public String createShareChange(@PathVariable("toDoId") Long toDoId, SharedListDto sharedListDto){
        sharedListDto.setFromId(getUserId());
        sharedListDto.setForChanges(true);
        sharedListDto.setToId(userService.readByUsername(sharedListDto.getUsername()).getId());
        restTemplate.postForObject(baseUrl + "/createShared", sharedListDto, SharedListDto.class);
        return "SuccessfulShared";
    }
}