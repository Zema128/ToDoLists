package by.ita.je.service.api;

import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.model.User;

import java.util.List;

public interface BusinessService {


    ToDo create(ToDo toDo, Long userId);

    SubTask createSubTask(SubTask subTask, Long TodoId);

    List<ToDo> readAll(Long userId);

    ToDo read (Long toDoId);

    ToDo update (ToDo toDo, Long id);

    void delete (Long id);
}