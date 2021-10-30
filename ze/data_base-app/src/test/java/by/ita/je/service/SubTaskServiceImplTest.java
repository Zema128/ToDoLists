package by.ita.je.service;

import by.ita.je.dao.SubTaskDao;
import by.ita.je.model.Invite;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubTaskServiceImplTest {

    @Mock
    private SubTaskDao subTaskDao;

    @InjectMocks
    private SubTaskServiceImpl subTaskService;

    private SubTask getSub(){
        ToDo toDo = new ToDo();
        return new SubTask(1L,"test", LocalDateTime.now(), LocalDateTime.now(), false, toDo);
    }

    @Test
    void create() {
        SubTask subTask = getSub();
        when(subTaskDao.save(subTask)).thenReturn(subTask);
        SubTask expected = getSub();
        SubTask actual = subTaskService.create(subTask);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.isSentMessage(), actual.isSentMessage());
        Mockito.verify(subTaskDao, Mockito.times(1)).save(subTask);
    }

    @Test
    void readById() {
        when(subTaskDao.findById(1L)).thenReturn(Optional.of(getSub()));
        SubTask subTask = subTaskService.readById(1L);
        assertEquals("test", subTask.getText());
        assertEquals(false, subTask.isSentMessage());
        verify(subTaskDao, times(1)).findById(1L);
    }

    @Test
    void deleteById() {
        subTaskService.deleteById(1L);
        verify(subTaskDao, times(1)).deleteById(1L);
    }

    @Test
    void update() {
        SubTask subTask = getSub();
        when(subTaskDao.findById(1L)).thenReturn(Optional.of(subTask));
        when(subTaskDao.save(subTask)).thenReturn(subTask);
        SubTask actual = getSub();
        SubTask expected = subTaskService.update(subTask, 1L);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.isSentMessage(), actual.isSentMessage());
    }

    @Test
    void sentMail() {
        SubTask subTask = getSub();
        when(subTaskDao.findById(1L)).thenReturn(Optional.of(subTask));
        when(subTaskDao.save(subTask)).thenReturn(subTask);
        subTaskService.sentMail(1L);
        verify(subTaskDao, times(1)).findById(1L);
        verify(subTaskDao, times(1)).save(subTask);
    }

    @Test
    void readAll() {
        List<SubTask> expectedSubTasks = new ArrayList<>();
        expectedSubTasks.add(getSub());
        expectedSubTasks.add(getSub());
        expectedSubTasks.add(getSub());
        when(subTaskDao.findAll()).thenReturn(expectedSubTasks);
        List<SubTask> actualSubTaskList = subTaskService.readAll();
        verify(subTaskDao, times(1)).findAll();
        for (SubTask sub : actualSubTaskList) {
            assertEquals("test", sub.getText());
            assertEquals(false, sub.isSentMessage());
        }
    }
}