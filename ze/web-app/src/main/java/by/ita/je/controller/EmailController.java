package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.EmailService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
    @Autowired
    public JavaMailSender emailSender;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    private Long userId;

    public EmailController(UserService userService, BCryptPasswordEncoder passwordEncoder, EmailService emailService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/password")
    public String password(Model model){
        model.addAttribute("pass", new UserDto());
        return "password";
    }

    @PostMapping("/password")
    public String sendSimpleEmail(UserDto userDto) {
        User user = userService.readByUsername(userDto.getUsername());
        this.userId = user.getId();
        String url = "http://localhost:8001/web-app/recoverpass";
        String html = "<h3>Hello Man! Here's a link to reset your password, go quickly!</h3><body>\n" +
                "  <p><a href='" + url + "'><h3>Here!</h3></a></p>\n" +
                " </body>";
        emailService.sendMessage(url, user.getEmail(), "Password recovery!","vladzemec@gmail.com", html);
        return "successful";
    }

    @GetMapping("/recoverpass")
    public String recoveryPassword(Model model){
        model.addAttribute("pass", new UserDto());
        return "RecoveryPassword";
    }

    @PostMapping("/recoverpass")
    public String recoveredPassword(UserDto userDto){
        userService.changePass(objectMapper.convertValue(userDto, User.class), userId);
        return "redirect:/";
    }
}