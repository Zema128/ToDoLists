package by.ita.je.controller;

import by.ita.je.dto.ToDoDto;
import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FriendController {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app";
    private final UserService userService;
    private final ObjectMapper objectMapper;

    private Long getUserId(){
        String id = String.valueOf(userService.getCurrentUserId());
        Long userId = Long.parseLong(id.replaceAll("[^0-9]", ""));
        return userId;
    }

    @GetMapping("/friends")
    public String listFriends(Model model){
        UserDto userDto = restTemplate.getForObject(baseUrl + "/friends/" + getUserId(), UserDto.class);
        List<User> friends = new ArrayList<>();
        for (User friend : userDto.getFriends()) {
            friends.add(userService.readById(friend.getId()));
        }
        model.addAttribute("friends", friends);
        return "Friendslist";
    }
}