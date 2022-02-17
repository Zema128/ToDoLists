package by.ita.je.dao;

import by.ita.je.model.ToDo;
import by.ita.je.model.enams.Categories;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearcherDaoIntegrationTest extends InviteDaoIntegrationTest {

    @Autowired
    SearcherDao searcherDao;

    @Test
    void searchToDoCategories() {
        List<ToDo> toDos = searcherDao.searchToDoCategories(1L, Categories.PERSONAL);
        assertNotNull(toDos);
        assertEquals(1, toDos.size());
        assertEquals(Categories.PERSONAL, toDos.get(0).getCategories());
    }

    @Test
    void searchBetweenDates() {
        List<ToDo> toDos = searcherDao.searchBetweenDates(1L,
                LocalDateTime.of(2021, 9, 1, 23, 23),
                LocalDateTime.of(2021, 12, 25, 23, 23));
        assertEquals(0, toDos.size());
    }
}