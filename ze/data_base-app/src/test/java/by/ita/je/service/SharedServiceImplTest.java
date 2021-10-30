package by.ita.je.service;

import by.ita.je.dao.SharedDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SharedServiceImplTest {

    @Mock
    private SharedDao sharedDao;

    @InjectMocks
    private SharedServiceImpl sharedService;

    @Test
    void createShared() {
        //База
    }

    @Test
    void sharedListsRead() {
        //База
    }

    @Test
    void sharedListsChange() {
        //База
    }

    @Test
    void delete() {
        //База
    }
}