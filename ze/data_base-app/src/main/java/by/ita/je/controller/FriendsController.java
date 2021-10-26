package by.ita.je.controller;

import by.ita.je.dto.InviteDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.dto.UserDto;
import by.ita.je.model.Invite;
import by.ita.je.service.InviteServiceImpl;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.InviteService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class FriendsController {
    private final ObjectMapper objectMapper;
    private final InviteService inviteService;
    private final BusinessService businessService;
    private final UserService userService;

    @GetMapping("/invite/{id}")
    public List<InviteDto> invitedList(@PathVariable("id") Long id){
        List<InviteDto> listInvited = inviteService.readAllByIdUserTo(id)
                .stream()
                .map(invite -> objectMapper.convertValue(invite, InviteDto.class))
                .collect(Collectors.toList());
        return listInvited;
    }

    @PostMapping("/addfriend")
    public InviteDto addFriend(@RequestBody InviteDto inviteDto){
        Invite invite = businessService.createInvite(objectMapper.convertValue(inviteDto, Invite.class));
        return objectMapper.convertValue(invite, InviteDto.class);
    }

    @GetMapping("/acceptfriend/{id}/{userId}")
    public void acceptFriend(@PathVariable("id") Long id,
                             @PathVariable("userId") Long userId){
        businessService.acceptFriend(id, userId);
        businessService.acceptSecondFriend(id, userId);
    }

    @DeleteMapping("/deniedfriend/{id}/{userId}")
    public void deniedFriend(@PathVariable("id") Long id,
                             @PathVariable("userId") Long userId){
        businessService.deniedFriend(id, userId);
    }

    @DeleteMapping("/deletefriend/{id}/{userId}")
    public void deleteFriend(@PathVariable("id") Long id,
                             @PathVariable("userId") Long userId){
        userService.deleteFriend(id, userId);
        userService.deleteFriend(userId, id);
    }
}