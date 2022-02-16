package by.ita.je.dao;

import by.ita.je.config.IntegrationTestDataBase;
import by.ita.je.model.Invite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InviteDaoIntegrationTest extends IntegrationTestDataBase {

    @Autowired
    InviteDao inviteDao;

    @Test
    void readAllByToUser_id() {
        List<Invite> invites = inviteDao.readAllByToUser_id(1L);
        assertEquals(1, invites.size());
        assertEquals(5, invites.get(0).getFromUser_id());
    }

    @Test
    void readByUserIdAndToId() {
        Invite invite = inviteDao.readByUserIdAndToId(1L, 2L);
        assertEquals(2L, invite.getToUser_id());
        assertEquals(1L, invite.getFromUser_id());
    }
}