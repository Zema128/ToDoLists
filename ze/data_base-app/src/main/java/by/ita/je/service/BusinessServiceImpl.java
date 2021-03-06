package by.ita.je.service;

import by.ita.je.exceptions.AccessException;
import by.ita.je.model.*;
import by.ita.je.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class BusinessServiceImpl implements BusinessService {
    private final UserService userService;
    private final ToDoService toDoService;
    private final SubTaskService subTaskService;
    private final InviteService inviteService;
    private final SharedService sharedService;

    @Override
    public Invite createInvite(Invite invite){
        Set<User> friends = userService.readById(invite.getFromUser_id()).getFriends();
        for (User user : friends) {
            if (user.getId() == invite.getToUser_id()) throw new AccessException("Уже есть в друзьях!");
        }
        return inviteService.create(invite);
    }

    @Override
    public void acceptFriend(Long id, Long userId){
        Invite invite = inviteService.readByUserIdAndToId(id, userId);
        User userFrom = userService.readById(invite.getFromUser_id());
        User userTo = userService.readById(invite.getToUser_id());
        Set<User> users = userTo.getFriends();
        users.add(userFrom);
        userService.update(users, invite.getToUser_id());
    }

    @Override
    public List<ToDo> sharedListsRead(Long toId){
        List<SharedList> sharedLists = sharedService.sharedListsRead(toId);
        List<ToDo> toDos = new ArrayList<>();
        for (SharedList shar:sharedLists) {
            toDos.add(toDoService.readById(shar.getToDoId()));
        }
        return toDos;
    }

    @Override
    public List<ToDo> sharedListsChange(Long toId){
        List<SharedList> sharedLists = sharedService.sharedListsChange(toId);
        List<ToDo> toDos = new ArrayList<>();
        for (SharedList shar:sharedLists) {
            toDos.add(toDoService.readById(shar.getToDoId()));
        }
        return toDos;
    }

    @Override
    public void acceptSecondFriend(Long id, Long userId){
        Invite invite = inviteService.readByUserIdAndToId(id, userId);
        User secondUserFrom = userService.readById(invite.getFromUser_id());
        User secondUserTo = userService.readById(invite.getToUser_id());
        Set<User> secondUsers = secondUserFrom.getFriends();
        secondUsers.add(secondUserTo);
        userService.update(secondUsers, invite.getFromUser_id());
        inviteService.deleteInvite(invite.getId());
    }

    @Override
    public void deniedFriend(Long id, Long userId){
        Invite invite = inviteService.readByUserIdAndToId(id, userId);
        inviteService.deleteInvite(invite.getId());
    }

    @Override
    public ToDo create(ToDo toDo, Long userId) {
        User user = userService.readById(userId);
        toDo.setUser(user);
        ToDo toDoCreated = toDoService.create(toDo);
        user.getToDos().add(toDoCreated);
        return toDoCreated;
    }

    @Override
    public SubTask createSubTask(SubTask subTask, Long toDoId){
        ToDo toDo = toDoService.readById(toDoId);
        subTask.setToDo(toDo);
        SubTask subTaskCreated = subTaskService.create(subTask);
        toDo.getSubTask().add(subTaskCreated);
        return subTaskCreated;
    }

    @Override
    public List<ToDo> readAll(Long userId){
        List<ToDo> list = new ArrayList<>();
        for (ToDo toDo : toDoService.readAll()) {
            if (toDo.getUser().getId().equals(userId)) {
                list.add(toDo);
            }
        }
        return list;
    }
}