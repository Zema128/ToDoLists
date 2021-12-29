package by.ita.je.controller;

import by.ita.je.config.MyConstants;
import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Controller
public class LoginController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/error")
    public String error403() {
        return "error";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("user", new UserDto());
        return "FormUser";
    }

    @PostMapping(value = "/create")
    public String created(UserDto userDto) {
        User user = objectMapper.convertValue(userDto, User.class);
        User newUser = userService.create(user);
        restTemplate.getForObject(MyConstants.BASE_URL + "/user/" + newUser.getId(), UserDto.class);
        return "redirect:/login";
    }
}