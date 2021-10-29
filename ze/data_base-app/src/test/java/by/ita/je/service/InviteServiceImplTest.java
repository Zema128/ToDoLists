package by.ita.je.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import by.ita.je.dao.InviteDao;
import by.ita.je.exceptions.AccessException;
import by.ita.je.model.Invite;
import by.ita.je.service.api.InviteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.Assert;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InviteServiceImplTest {

    @Mock
    private InviteDao inviteDao;

    @InjectMocks
    private InviteServiceImpl inviteService;

    private Invite getInvite(){
        Invite invite = new Invite(1L,1L,3L);
        return invite;
    }

    @Test
    void create() {
        Invite invite = getInvite();
        when(inviteDao.save(invite)).thenReturn(invite);
        Invite excpected = getInvite();
        Invite actual = inviteService.create(invite);
        assertEquals(excpected, actual);
        Mockito.verify(inviteDao, Mockito.times(1)).save(invite);
    }

    @Test
    void readAllByIdUserTo() {
        //База
    }

    @Test
    void readByUserIdAndToId() {
        //База
    }

    @Test
    void readById() {
        when(inviteDao.findById(1L)).thenReturn(Optional.of(getInvite()));
        Invite invite = inviteService.readById(1L);
        assertEquals(Long.valueOf("3"), invite.getToUser_id());
        assertEquals(Long.valueOf("1"), invite.getFromUser_id());
        verify(inviteDao, times(1)).findById(1L);
    }

    @Test
    void deleteInvite() {
        inviteService.deleteInvite(1L);
        verify(inviteDao, times(1)).deleteById(1L);
    }
}