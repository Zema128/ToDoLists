package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Controller
public class LoginController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app";

    @GetMapping("/")
    public String login(){
        return "index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("user", new UserDto());
        return "FormUser";
    }

    @PostMapping(value = "/create")
    public String created(@ModelAttribute UserDto userDto) {
        restTemplate.postForObject(baseUrl + "/user", userDto, UserDto.class);
        return "redirect:/";
    }

    @ModelAttribute("user")
    private UserDto userDto (){
        return new UserDto();
    }
}