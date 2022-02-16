package by.ita.je.service;

import by.ita.je.model.ToDo;
import by.ita.je.model.enams.Categories;
import by.ita.je.service.api.SearcherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearcherServiceImplTest {

    @Autowired
    SearcherService searcherService;

    @Test
    public void listCategories() {
        List<ToDo> toDos = searcherService.listCategories(1L, Categories.PERSONAL);
        System.out.println(toDos.get(0).getText());
    }

}