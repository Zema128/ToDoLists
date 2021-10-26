package by.ita.je.dao;

import by.ita.je.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteDao extends CrudRepository<Invite, Long> {

    @Query(value = "SELECT i FROM Invite i WHERE i.toUser_id = :id")
    public List<Invite> readAllByToUser_id (Long id);

    @Query(value = "SELECT i FROM Invite i WHERE (i.fromUser_id = :toId) AND (i.toUser_id = :userId)")
    public Invite readByUserIdAndToId(Long toId, Long userId);
}