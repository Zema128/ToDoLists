package by.ita.je.dao;

import by.ita.je.model.SharedList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedDao extends CrudRepository<SharedList, Long> {

    @Query(value = "SELECT s FROM SharedList s WHERE (s.toId = :toId) AND (s.forChanges = :read)")
    public List<SharedList> readAllByUserIdRead(Long toId, boolean read);

    @Query(value = "SELECT s FROM SharedList s WHERE (s.toId = :toId) AND (s.forChanges = :change)")
    public List<SharedList> readAllByUserIdChange(Long toId, boolean change);

    @Query(value = "SELECT s FROM SharedList s WHERE s.toId = :toId")
    public List<SharedList> readAllByToId(Long toId);

    @Query(value = "SELECT s FROM SharedList s WHERE (s.toDoId = :toDoId) AND (s.toId = :toId)")
    public SharedList readByToDoIdToId(Long toDoId, Long toId);
}