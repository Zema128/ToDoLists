package by.ita.je.service;

import by.ita.je.dao.SearcherDao;
import by.ita.je.model.ToDo;
import by.ita.je.model.enams.Categories;
import by.ita.je.service.api.SearcherService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class SearcherServiceImpl implements SearcherService {

    private final SearcherDao searcherDao;

    @Override
    public List<ToDo> listCategories(Long userId, Categories categories){
        return searcherDao.searchToDoCategories(userId, categories);
    }

    @Override
    public List<ToDo> listBetweenDated(Long userId, LocalDateTime fromDate, LocalDateTime toDate){
        return searcherDao.searchBetweenDates(userId, fromDate, toDate);
    }
}