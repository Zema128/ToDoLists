package by.ita.je.service.api;

import by.ita.je.dto.ToDoDto;

public interface EmailService {

    void sendNotification();

    void sentToDo(Long toDoId);

    void sentSubTask(Long subTaskId);

    void sendMessage(String setTo, String setSubject, String setFrom, String html);

}
