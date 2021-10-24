package by.ita.je.dao;

import by.ita.je.model.ToDo;
import by.ita.je.model.enams.Categories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SearcherDao extends CrudRepository<ToDo, Long> {

    @Query(value = "SELECT t FROM ToDo t WHERE (t.categories = :categories) AND (t.user.id = :userId)")
    List<ToDo> searchToDoCategories(Long userId, Categories categories);

    @Query(value = "SELECT t FROM ToDo t WHERE (t.user.id = :userId) AND (t.timeNotification BETWEEN :fromdate AND :todate)")
    List<ToDo> searchBetweenDates(Long userId, @Param("fromdate") LocalDateTime fromDate, @Param("todate") LocalDateTime toDate);
}