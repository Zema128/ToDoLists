package by.ita.je.service.api;

import by.ita.je.model.ToDo;
import by.ita.je.model.enams.Categories;

import java.time.LocalDateTime;
import java.util.List;

public interface SearcherService {
    List<ToDo> listCategories(Long userId, Categories categories);

    List<ToDo> listBetweenDated(Long userId, LocalDateTime fromDate, LocalDateTime toDate);
}
