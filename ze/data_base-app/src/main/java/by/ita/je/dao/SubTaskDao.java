package by.ita.je.dao;

import by.ita.je.model.SubTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTaskDao extends CrudRepository<SubTask, Long> {
}