package by.ita.je.service.api;

import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;

import java.util.List;

public interface ToDoService {
    ToDo create(ToDo toDo);

    ToDo readById(Long id);

    List<ToDo> readAll();

    List<SubTask> readAllSubtasks(Long todoId);

    void deleteById(Long id);

    void sentMail(Long toDoId);

    ToDo update(ToDo toDo, Long id);
}