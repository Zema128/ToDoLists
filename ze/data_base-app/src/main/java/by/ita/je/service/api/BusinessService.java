package by.ita.je.service.api;

import by.ita.je.model.ToDo;
import by.ita.je.model.User;

import java.util.List;

public interface BusinessService {


    ToDo create(ToDo toDo, Long userId);

    List<ToDo> readAll(Long userId);

    ToDo read (Long toDoId);

    User update (User user, Long id);

    void delete (Long id);
}