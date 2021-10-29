package by.ita.je.service;

import by.ita.je.dao.SubTaskDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubTaskServiceImplTest {

    @Mock
    SubTaskDao subTaskDao;

    @InjectMocks
    SubTaskServiceImpl subTaskService;

    @Test
    void create() {
    }

    @Test
    void readById() {
    }

    @Test
    void deleteById() {
        subTaskService.deleteById(1L);
        verify(subTaskDao, times(1)).deleteById(1L);
    }

    @Test
    void update() {
    }

    @Test
    void sentMail() {
    }

    @Test
    void readAll() {
    }
}