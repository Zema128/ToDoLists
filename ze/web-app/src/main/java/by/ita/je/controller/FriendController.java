package by.ita.je.controller;

import by.ita.je.config.ClientConfig;
import by.ita.je.dto.InviteDto;
import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FriendController {
    private final RestTemplate restTemplate;
    private final UserService userService;
    private final ClientConfig clientConfig;



    private Long getUserId(){
        String id = String.valueOf(userService.getCurrentUserId());
        Long userId = Long.parseLong(id.replaceAll("[^0-9]", ""));
        return userId;
    }

    @GetMapping("/friends")
    public String listFriends(Model model){
        ResponseEntity<Long[]> userDtos = restTemplate.getForEntity(clientConfig.getUrl() + "/friends/" + getUserId(),
                Long[].class);
        List<Long> listUser = Arrays.asList(userDtos.getBody());
        List<User> friends = new ArrayList<>();
        for (Long friend : listUser) {
            friends.add(userService.readById(friend));
        }
        model.addAttribute("friends", friends);
        ResponseEntity<InviteDto[]> responseEntity =
                restTemplate.getForEntity(clientConfig.getUrl() + "/invite/" + getUserId(), InviteDto[].class);
        List<InviteDto> listInvites = Arrays.asList(responseEntity.getBody());
        List<User> invitedList = new ArrayList<>();
        for (InviteDto invite : listInvites) {
            invitedList.add(userService.readById(invite.getFromUser_id()));
        }
        model.addAttribute("invites", invitedList);
        return "Friendslist";
    }

    @GetMapping("/addfriend")
    public String addFriend(Model model){
        model.addAttribute("user", new UserDto());
        return "AddFriends";
    }

    @PostMapping("/addfriend")
    public String sendInvite(UserDto userDto){
        User user = userService.readByUsername(userDto.getUsername());
        if (user == null) throw new RuntimeException("?????? ???????????? ????????????????????????!");
        InviteDto invite = new InviteDto();
        invite.setFromUser_id(getUserId());
        invite.setToUser_id(user.getId());
        restTemplate.postForObject(clientConfig.getUrl() + "/addfriend", invite, InviteDto.class);
        return "redirect:/friends";
    }

    @GetMapping("/acceptfriend/{id}")
    public String acceptFriend(@PathVariable("id") Long id){
        restTemplate.getForObject(clientConfig.getUrl() + "/acceptfriend/" + id + "/" + getUserId(), InviteDto.class);
        return "redirect:/friends";
    }

    @GetMapping("/deniedfriend/{id}")
    public String deniedFriend(@PathVariable("id") Long id){
        restTemplate.delete(clientConfig.getUrl() + "/deniedfriend/" + id + "/" +getUserId());
        return "redirect:/friends";
    }

    @GetMapping("/deletefriend/{id}")
    public String deleteFriend(@PathVariable("id") Long id){
        restTemplate.delete(clientConfig.getUrl() + "/deletefriend/" + id + "/" + getUserId());
        return "redirect:/friends";
    }
}