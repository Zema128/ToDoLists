package by.ita.je.service;

import by.ita.je.dao.SubTaskDao;
import by.ita.je.model.SubTask;
import by.ita.je.service.api.SubTaskService;
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
    public List<SubTask> readAll() {
        List<SubTask> subTasks = new ArrayList<>();
        subTaskDao.findAll().forEach(subTasks::add);
        return subTasks;
    }

    @Override
    public void deleteById(Long id) {
        subTaskDao.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Long> list) {
        subTaskDao.deleteAllById(list);
    }

    @Override
    public SubTask update(SubTask subTask, Long id) {
        SubTask subTaskUpd = subTaskDao.findById(id).orElseThrow(() -> new RuntimeException("UPDATE"));
        subTaskUpd.setText(subTask.getText());
        subTaskUpd.setTimeNotification(subTask.getTimeNotification());
        return subTaskDao.save(subTaskUpd);
    }
}