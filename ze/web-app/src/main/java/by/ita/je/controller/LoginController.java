package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.service.api.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Controller
public class LoginController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app";
    private final BusinessService businessService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("user", new UserDto());
        return "FormUser";
    }

    @PostMapping(value = "/create")
    public String created(UserDto userDto) {
        restTemplate.postForObject(baseUrl + "/user", businessService.create(userDto), UserDto.class);
        return "redirect:/login";
    }
}