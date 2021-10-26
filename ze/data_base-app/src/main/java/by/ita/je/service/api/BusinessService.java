package by.ita.je.service.api;

import by.ita.je.model.Invite;
import by.ita.je.model.SubTask;
import by.ita.je.model.ToDo;
import by.ita.je.model.User;

import java.util.List;

public interface BusinessService {


    Invite createInvite(Invite invite);

    void acceptFriend(Long id, Long userId);

    void acceptSecondFriend(Long id, Long userId);

    void deniedFriend(Long id, Long userId);

    ToDo create(ToDo toDo, Long userId);

    SubTask createSubTask(SubTask subTask, Long TodoId);

    List<ToDo> readAll(Long userId);

    ToDo read (Long toDoId);

    ToDo update (ToDo toDo, Long id);

    void delete (Long id);
}