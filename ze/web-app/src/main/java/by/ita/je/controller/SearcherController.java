package by.ita.je.controller;

import by.ita.je.dto.SearcherDateDto;
import by.ita.je.dto.SearcherDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
public class SearcherController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://database-app:8003/data_base-app";
    private final UserService userService;

    private Long getUserId(){
        String id = String.valueOf(userService.getCurrentUserId());
        Long userId = Long.parseLong(id.replaceAll("[^0-9]", ""));
        return userId;
    }

    @GetMapping("/searchpersonal")
    public String searchByPersonal(Model model){
        SearcherDto searcherDto = new SearcherDto(getUserId(), "PERSONAL");
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.postForEntity(baseUrl + "/searchcategories", searcherDto, ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("categor", list);
        return "PersonalList";
    }

    @GetMapping("/searchwork")
    public String searchByWork(Model model){
        SearcherDto searcherDto = new SearcherDto(getUserId(), "WORK");
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.postForEntity(baseUrl + "/searchcategories", searcherDto, ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("categor", list);
        return "WorkList";
    }

    @GetMapping("/searchusers")
    public String searchByUsers(Model model){
        SearcherDto searcherDto = new SearcherDto(getUserId(), "USERS");
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.postForEntity(baseUrl + "/searchcategories", searcherDto, ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("categor", list);
        return "UsersList";
    }

    @GetMapping("/betweendates")
    public String betweenDates(
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo,
                               Model model){
        LocalDateTime from = LocalDateTime.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime to = LocalDateTime.parse(dateTo, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        SearcherDateDto searcherDateDto = new SearcherDateDto(from, to);
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.postForEntity(baseUrl + "/betweendates/" + getUserId(), searcherDateDto, ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("categor", list);
        return "BetweenDates";
    }

    @GetMapping("/todaytasks")
    public String searchTodayTasks(Model model){
        LocalDateTime from = LocalDateTime.now().toLocalDate().atTime(LocalTime.MIDNIGHT);
        LocalDateTime to = LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX);
        System.out.println("From: " + from + " to: " + to);
        SearcherDateDto searcherDateDto = new SearcherDateDto(from, to);
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.postForEntity(baseUrl + "/betweendates/" + getUserId(), searcherDateDto, ToDoDto[].class);
        List<ToDoDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("categor", list);
        return "TodayTasks";
    }
}