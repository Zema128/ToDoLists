package by.ita.je.dao;

import by.ita.je.config.IntegrationTestDataBase;
import by.ita.je.model.SharedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SharedDaoIntegrationTest extends IntegrationTestDataBase {
    @Autowired
    SharedDao sharedDao;

    @Test
    void readAllByUserIdRead() {
        List<SharedList> sharedLists = sharedDao.readAllByUserIdRead(1L, false);
        assertNotNull(sharedLists);
    }

    @Test
    void readAllByUserIdChange() {
        List<SharedList> sharedLists = sharedDao.readAllByUserIdChange(1L, true);
        assertNotNull(sharedLists);
    }

    @Test
    void readAllByToId() {
        List<SharedList> sharedLists = sharedDao.readAllByToId(1L);
        assertNotNull(sharedLists);
    }

    @Test
    void readByToDoIdToId() {
        SharedList shared = sharedDao.readByToDoIdToId(1L, 1L);
        assertNull(shared);
    }
}