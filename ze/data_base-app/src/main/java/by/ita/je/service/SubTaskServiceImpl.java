package by.ita.je.service;

import by.ita.je.dao.SubTaskDao;
import by.ita.je.exceptions.NotFoundException;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.SubTaskService;
import by.ita.je.service.api.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@Transactional
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskDao subTaskDao;

    @Override
    public SubTask create(SubTask subTask) {
        subTask.setTimeCreated(LocalDateTime.now());
        return subTaskDao.save(subTask);
    }

    @Override
    public SubTask readById(Long id) {
        return subTaskDao.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        subTaskDao.deleteById(id);
    }

    @Override
    public SubTask update(SubTask subTask, Long id) {
        SubTask subTaskUpd = subTaskDao.findById(id).orElseThrow(() -> new NotFoundException("SubTask not found!"));
        if (subTaskUpd.getTimeNotification() != subTask.getTimeNotification()){
            subTaskUpd.setSentMessage(false);
        }
        subTaskUpd.setText(subTask.getText());
        subTaskUpd.setTimeNotification(subTask.getTimeNotification());
        return subTaskDao.save(subTaskUpd);
    }

    @Override
    public void sentMail(Long subTaskId){
        SubTask subTask = readById(subTaskId);
        subTask.setSentMessage(true);
        subTaskDao.save(subTask);
    }

    @Override
    public List<SubTask> readAll() {
        List<SubTask> subTasks = new ArrayList<>();
        subTaskDao.findAll().forEach(subTasks::add);
        return subTasks;
    }
}