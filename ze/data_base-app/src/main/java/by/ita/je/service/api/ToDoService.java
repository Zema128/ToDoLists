package by.ita.je.service.api;

import by.ita.je.model.ToDo;

import java.util.List;

public interface ToDoService {
    ToDo create(ToDo toDo);

    ToDo readById(Long id);

    List<ToDo> readAll();

    void deleteById(Long id);

    void deleteAllById(List<Long> list);

    ToDo update(ToDo toDo, Long id);
}