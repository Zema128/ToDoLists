package by.ita.je.controller;

import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {
    @Autowired
    public JavaMailSender emailSender;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmailController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/password")
    public String password(Model model){
        model.addAttribute("pass", new UserDto());
        return "password";
    }

    @PostMapping("/password")
    public String sendSimpleEmail(UserDto userDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        User user = userService.readByUsername(userDto.getUsername());
        message.setTo(user.getEmail());
        message.setSubject("Восстановление пароля!");
        message.setText("You username: " + user.getUsername() + " You password: " + user.getPassword());
        this.emailSender.send(message);
        return HttpHeaders.ACCEPT;
    }
}