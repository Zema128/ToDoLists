package by.ita.je.service.api;

import by.ita.je.model.Invite;

import java.util.List;

public interface InviteService {
    List<Invite> readAllByIdUserTo(Long id);

    Invite create(Invite invite);

    Invite readByUserIdAndToId(Long toId, Long userId);

    Invite readById(Long id);

    void deleteInvite(Long id);
}
