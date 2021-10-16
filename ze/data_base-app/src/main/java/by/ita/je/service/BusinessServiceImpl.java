package by.ita.je.service;

import by.ita.je.model.User;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.SubTaskService;
import by.ita.je.service.api.ToDoService;
import by.ita.je.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final UserService userService;
    private final ToDoService toDoService;
    private final SubTaskService subTaskService;

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User read(Long id) {
        return null;
    }

    @Override
    public User update(User user, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        toDoService.deleteById(id);
    }
}