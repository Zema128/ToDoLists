package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.model.ToDo;
import by.ita.je.model.User;
import by.ita.je.model.enams.Categories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    private User getUser(){
        List<ToDo> listToDo = new ArrayList<>();
        listToDo.add(new ToDo(1L, LocalDateTime.now(), LocalDateTime.now(), "test",
                false, false, null, Categories.USERS, null));
        listToDo.add(new ToDo(2L, LocalDateTime.now(), LocalDateTime.now(), "test",
                false, false, null, Categories.USERS, null));
        Set<User> toDoSet = new HashSet<>();
        return new User(1L, LocalDateTime.now(), listToDo, toDoSet);
    }

    @Test
    void create() {
        User user = getUser();
        when(userDao.save(user)).thenReturn(user);
        User expected = getUser();
        User actual = userService.create(user);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void readById() {
        when(userDao.findById(1L)).thenReturn(Optional.of(getUser()));
        User user = userService.readById(1L);
        assertEquals(Long.valueOf("1"), user.getId());
        verify(userDao, times(1)).findById(1L);
    }

    @Test
    void readFriendsList() {
    }

    @Test
    void deleteFriend() {

    }

    @Test
    void update() {
        User user = getUser();
        when(userDao.findById(1L)).thenReturn(Optional.of(user));
        when(userDao.save(user)).thenReturn(user);
        User actual = getUser();
        Set<User> userSet = new HashSet<>();
        userService.update(userSet, 1L);
        Mockito.verify(userDao, times(1)).save(user);
    }

    @Test
    void readAll() {
        List<User> expectedUser = new ArrayList<>();
        expectedUser.add(getUser());
        when(userDao.findAll()).thenReturn(expectedUser);
        List<User> actualUsers = userService.readAll();
        verify(userDao, times(1)).findAll();
        for (User user : actualUsers) {
            assertEquals(Long.valueOf("1"), user.getId());
        }
    }
}