package by.ita.je.service;

import by.ita.je.dao.InviteDao;
import by.ita.je.exceptions.AccessException;
import by.ita.je.model.Invite;
import by.ita.je.service.api.InviteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class InviteServiceImpl implements InviteService {
    private final InviteDao inviteDao;

    @Override
    public List<Invite> readAllByIdUserTo(Long id){
        return inviteDao.readAllByToUser_id(id);
    }

    @Override
    public Invite create (Invite invite){
        List<Invite> invites = inviteDao.readAllByToUser_id(invite.getToUser_id());
        for (Invite inv : invites) {
            if (inv.getToUser_id() == invite.getToUser_id() && inv.getFromUser_id() == invite.getFromUser_id())
                throw new AccessException("Заявка уже отправлена!");
            if (invite.getToUser_id() == inv.getFromUser_id() || invite.getFromUser_id() == inv.getToUser_id())
                throw new AccessException("Нельзя самого себя добавлять в друзья!");
        }
        return inviteDao.save(invite);
    }

    @Override
    public Invite readByUserIdAndToId(Long toId, Long userId){
        return inviteDao.readByUserIdAndToId(toId, userId);
    }

    @Override
    public Invite readById(Long id){
        return inviteDao.findById(id).get();
    }

    @Override
    public void deleteInvite(Long id){
        inviteDao.deleteById(id);
    }

}