package by.ita.je.service;

import by.ita.je.dao.SubTaskDao;
import by.ita.je.model.SubTask;
import by.ita.je.service.api.SubTaskService;
import by.ita.je.service.api.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskDao subTaskDao;

    @Override
    public SubTask create(SubTask subTask) {
        subTask.setTimeCreated(ZonedDateTime.now());
        return subTaskDao.save(subTask);
    }

    @Override
    public SubTask readById(Long id) {
        return subTaskDao.findById(id).get();
    }

    @Override
    public List<SubTask> readAll(Long todoId) {
        List<SubTask> list = new ArrayList<>();
        subTaskDao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void deleteById(Long id) {
        subTaskDao.deleteById(id);
    }

    @Override
    public SubTask update(SubTask subTask, Long id) {
        SubTask subTaskUpd = subTaskDao.findById(id).orElseThrow(() -> new RuntimeException("UPDATE"));
        subTaskUpd.setText(subTask.getText());
        subTaskUpd.setTimeNotification(subTask.getTimeNotification());
        return subTaskDao.save(subTaskUpd);
    }
}