package by.ita.je.service.api;

public interface EmailService {

    void sendMessage(String url, String setTo, String setSubject, String setFrom, String html);
}
