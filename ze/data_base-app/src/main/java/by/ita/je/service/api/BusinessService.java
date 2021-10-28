package by.ita.je.service.api;

import by.ita.je.model.Invite;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;

import java.util.List;

public interface BusinessService {


    Invite createInvite(Invite invite);

    void acceptFriend(Long id, Long userId);

    List<ToDo> sharedListsRead(Long toId);

    List<ToDo> sharedListsChange(Long toId);

    void acceptSecondFriend(Long id, Long userId);

    void deniedFriend(Long id, Long userId);

    ToDo create(ToDo toDo, Long userId);

    SubTask createSubTask(SubTask subTask, Long TodoId);

    List<ToDo> readAll(Long userId);
}