package by.ita.je.service;

import by.ita.je.service.api.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMessage(String url, String setTo, String setSubject, String setFrom, String html) {
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