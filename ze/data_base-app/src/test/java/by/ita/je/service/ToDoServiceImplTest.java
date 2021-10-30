package by.ita.je.service;

import by.ita.je.dao.ToDoDao;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.model.User;
import by.ita.je.model.enams.Categories;
import org.junit.jupiter.api.Assertions;
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
class ToDoServiceImplTest {

    @Mock
    private ToDoDao toDoDao;

    @InjectMocks
    private ToDoServiceImpl toDoService;

    private ToDo getToDo(){
        User user = new User();
        List<SubTask> list = new ArrayList<>();
        list.add(new SubTask(1L,"test", LocalDateTime.now(), LocalDateTime.now(), false, new ToDo()));
        list.add(new SubTask(2L,"test", LocalDateTime.now(), LocalDateTime.now(), false, new ToDo()));
        return new ToDo(1L, LocalDateTime.now(), LocalDateTime.now(), "test",
                false, false, user, Categories.USERS, list);
    }

    @Test
    void create() {
        ToDo toDo = getToDo();
        when(toDoDao.save(toDo)).thenReturn(toDo);
        ToDo excepted = getToDo();
        ToDo actual = toDoService.create(toDo);
        assertEquals(excepted.getText(), actual.getText());
        assertEquals(excepted.isDone(), actual.isDone());
        assertEquals(excepted.isSentMessage(), actual.isSentMessage());
        Mockito.verify(toDoDao, times(1)).save(toDo);
    }

    @Test
    void readById() {
        when(toDoDao.findById(1L)).thenReturn(Optional.of(getToDo()));
        ToDo toDo = toDoService.readById(1L);
        assertEquals("test", toDo.getText());
        assertEquals(false, toDo.isDone());
        assertEquals(false, toDo.isSentMessage());
        verify(toDoDao, times(1)).findById(1L);
    }

    @Test
    void readAll() {
        List<ToDo> expectedToDo = new ArrayList<>();
        expectedToDo.add(getToDo());
        expectedToDo.add(getToDo());
        expectedToDo.add(getToDo());
        when(toDoDao.findAll()).thenReturn(expectedToDo);
        List<ToDo> actualToDoList = toDoService.readAll();
        verify(toDoDao, times(1)).findAll();
        for (ToDo toDo : actualToDoList) {
            assertEquals("test", toDo.getText());
            assertEquals(false, toDo.isDone());
            assertEquals(false, toDo.isSentMessage());
        }
    }

    @Test
    void readAllSubtasks() {
        when(toDoDao.findById(1L)).thenReturn(Optional.of(getToDo()));
        List<SubTask> actualSubTask = toDoService.readAllSubtasks(1L);
        for (SubTask sub:actualSubTask) {
            assertEquals("test", sub.getText());
            assertEquals(false, sub.isSentMessage());
        }
    }

    @Test
    void deleteById() {
        toDoService.deleteById(1L);
        verify(toDoDao, times(1)).deleteById(1L);
    }

    @Test
    void sentMail() {
        ToDo toDo = getToDo();
        when(toDoDao.findById(1L)).thenReturn(Optional.of(toDo));
        when(toDoDao.save(toDo)).thenReturn(toDo);
        toDoService.sentMail(1L);
        verify(toDoDao, times(1)).findById(1L);
        verify(toDoDao, times(1)).save(toDo);
    }

    @Test
    void update() {
        ToDo toDo = getToDo();
        when(toDoDao.findById(1L)).thenReturn(Optional.of(toDo));
        when(toDoDao.save(toDo)).thenReturn(toDo);
        ToDo actual = getToDo();
        ToDo expected = toDoService.update(toDo, 1L);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.isSentMessage(), actual.isSentMessage());
        assertEquals(expected.isDone(), actual.isDone());
    }
}