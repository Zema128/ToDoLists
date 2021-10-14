package by.ita.je.dao;

import by.ita.je.model.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoDao extends CrudRepository<ToDo, Long> {
}