package by.ita.je.service;

import by.ita.je.dao.ToDoDao;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class ToDoServiceImpl implements ToDoService {

    private final ToDoDao toDoDao;

    @Override
    public ToDo create(ToDo toDo) {
        toDo.setDone(false);
        toDo.setTimeCreated(ZonedDateTime.now());
        return toDoDao.save(toDo);
    }

    @Override
    public ToDo readById(Long id) {
        return toDoDao.findById(id).get();
    }

    @Override
    public List<ToDo> readAll() {
        List<ToDo> toDos = new ArrayList<>();
        toDoDao.findAll().forEach(toDos::add);
        return toDos;
    }

    @Override
    public void deleteById(Long id) {
        toDoDao.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Long> list) {
        toDoDao.deleteAllById(list);
    }

    @Override
    public ToDo update(ToDo toDo, Long id) {
        ToDo toDoUpd = toDoDao.findById(id).orElseThrow(() -> new RuntimeException("UPDATE"));
        toDoUpd.setDone(toDo.isDone());
        toDoUpd.setText(toDo.getText());
        toDoUpd.setTimeNotification(toDo.getTimeNotification());
        return toDoDao.save(toDoUpd);
    }
}