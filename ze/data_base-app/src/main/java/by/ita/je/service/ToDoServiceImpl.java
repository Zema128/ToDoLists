package by.ita.je.service;

import by.ita.je.dao.ToDoDao;
import by.ita.je.exceptions.NotFoundException;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@Transactional
public class ToDoServiceImpl implements ToDoService {

    private final ToDoDao toDoDao;

    @Override
    public ToDo create(ToDo toDo) {
        toDo.setDone(false);
        toDo.setTimeCreated(LocalDateTime.now());
        return toDoDao.save(toDo);
    }

    @Override
    public ToDo readById(Long id) {
        return toDoDao.findById(id).orElseThrow(() -> new RuntimeException("ToDo Nine!"));
    }

    @Override
    public List<ToDo> readAll() {
        List<ToDo> toDos = new ArrayList<>();
        toDoDao.findAll().forEach(toDos::add);
        return toDos;
    }

    @Override
    public List<SubTask> readAllSubtasks(Long todoId) {
        ToDo toDo = toDoDao.findById(todoId).get();
        return toDo.getSubTask();
    }

    @Override
    public void deleteById(Long id) {
        toDoDao.deleteById(id);
    }

    @Override
    public void sentMail(Long toDoId){
        ToDo toDo = readById(toDoId);
        toDo.setSentMessage(true);
        toDoDao.save(toDo);
    }

    @Override
    public ToDo update(ToDo toDo, Long id) {
        ToDo toDoUpd = toDoDao.findById(id).orElseThrow(() -> new NotFoundException("ToDo not found!"));
        if (toDoUpd.getTimeNotification() != toDo.getTimeNotification()){
            toDoUpd.setSentMessage(false);
        }
        toDoUpd.setDone(toDo.isDone());
        toDoUpd.setText(toDo.getText());
        toDoUpd.setTimeNotification(toDo.getTimeNotification());
        toDoUpd.setCategories(toDo.getCategories());
        return toDoDao.save(toDoUpd);
    }
}