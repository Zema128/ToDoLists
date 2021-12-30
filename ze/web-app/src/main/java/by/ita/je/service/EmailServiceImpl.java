package by.ita.je.service;

import by.ita.je.config.ClientConfig;
import by.ita.je.dto.SubTaskDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.User;
import by.ita.je.service.api.EmailService;
import by.ita.je.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private final RestTemplate restTemplate;
    private final UserService userService;
    private final ClientConfig clientConfig;

    public EmailServiceImpl(RestTemplate restTemplate, UserService userService, ClientConfig clientConfig) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.clientConfig = clientConfig;
    }

    @Override
    @Scheduled(fixedDelay = 10000)
    public void sendNotification(){
        ResponseEntity<ToDoDto[]> responseEntity =
                restTemplate.getForEntity(clientConfig.getUrl() + "/todosformessage", ToDoDto[].class);
        List<ToDoDto> toDos = Arrays.asList(responseEntity.getBody());
        for (ToDoDto t : toDos) {
            if (t.getTimeNotification() != null){
                User user = userService.readById(t.getUserId());
                String html = "<h3>Hello " + user.getUsername() + "! Your time came!</h3><body>\n" +
                        "<h2>Text: " + t.getText() + "</h2></body>";
                if (LocalDateTime.now().plusHours(3).isAfter(t.getTimeNotification()) && t.isSentMessage() == false) {
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
                    if (LocalDateTime.now().plusHours(3).isAfter(s.getTimeNotification())) {
                        sendMessage(user.getEmail(), "Notification!", "vladzemec@gmail.com", html);
                        sentSubTask(s.getId());
                    }
                }
            }
        }
    }

    @Override
    public void sentToDo(Long toDoId){
        restTemplate.getForObject(clientConfig.getUrl() + "/senttodo/" + toDoId, ToDoDto.class);
    }

    @Override
    public void sentSubTask(Long subTaskId){
        restTemplate.getForObject(clientConfig.getUrl() + "/sentsubtask/" + subTaskId, SubTaskDto.class);
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