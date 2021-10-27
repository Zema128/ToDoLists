package by.ita.je.service;

import by.ita.je.dto.SubTaskDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.User;
import by.ita.je.service.api.EmailService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private final RestTemplate restTemplate;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:8003/data_base-app";

    public EmailServiceImpl(RestTemplate restTemplate, UserService userService, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Override
    @Scheduled(fixedDelay = 30000)
    public void sendNotification(){
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.getForEntity(baseUrl + "/todosformessage", ToDoDto[].class);
        List<ToDoDto> toDos = Arrays.asList(responseEntity.getBody());
        for (ToDoDto t : toDos) {
            if (t.getTimeNotification() != null){
                User user = userService.readById(t.getUserId());
                String html = "<h3>Hello " + user.getUsername() + "! Your time came!</h3><body>\n" +
                        "<h2>Text: " + t.getText() + "</h2></body>";
                if (LocalDateTime.now().isAfter(t.getTimeNotification()) && t.isSentMessage() == false) {
                    sendMessage(user.getEmail(), "Notification!", "vladzemec@gmail.com", html);
                    sentToDo(t.getId());
                }
            }
            List<SubTaskDto> subTasks = t.getSubTask();
            for (SubTaskDto s : subTasks) {
                if (s.getTimeNotification() != null && s.isSentMessage() == false){
                    User user = userService.readById(t.getUserId());
                    String html = "<h3>Hello " + user.getUsername() + "! Your time came!</h3><body>\n" +
                            "<h2>Text: " + s.getText() + "</h2></body>";
                    if (LocalDateTime.now().isAfter(s.getTimeNotification())) {
                        sendMessage(user.getEmail(), "Notification!", "vladzemec@gmail.com", html);
                        sentSubTask(s.getId());
                    }
                }
            }
        }
    }

    @Override
    public void sentToDo(Long toDoId){
        restTemplate.getForObject(baseUrl + "/senttodo/" + toDoId, ToDoDto.class);
    }

    @Override
    public void sentSubTask(Long subTaskId){
        restTemplate.getForObject(baseUrl + "/sentsubtask/" + subTaskId, SubTaskDto.class);
    }

    @Override
    public void sendMessage(String setTo, String setSubject, String setFrom, String html) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            message.setContent(html, "text/html");
            helper = new MimeMessageHelper(message, false, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(setTo);
            helper.setSubject(setSubject);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.getMessage();
        }
    }
}