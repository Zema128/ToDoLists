package by.ita.je.service;

import by.ita.je.model.ToDo;
import by.ita.je.model.User;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.SubTaskService;
import by.ita.je.service.api.ToDoService;
import by.ita.je.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final UserService userService;
    private final ToDoService toDoService;
    private final SubTaskService subTaskService;

    @Override
    public ToDo create(ToDo toDo, Long userId) {
        User user = userService.readById(userId);
        toDo.setUser(user);
        ToDo toDoCreated = toDoService.create(toDo);
        user.getToDos().add(toDoCreated);
        return toDoCreated;
    }

    @Override
    public List<ToDo> readAll(Long userId){
        List<ToDo> list = toDoService.readAll().stream().filter(toDo -> toDo.getUser().getId().equals(userId)).toList();
        return list;
    }

    @Override
    public ToDo read(Long toDoId) {
        return toDoService.readById(toDoId);
    }

    @Override
    public ToDo update(ToDo toDo, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        toDoService.deleteById(id);
    }
}