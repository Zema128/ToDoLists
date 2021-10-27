package by.ita.je.service;

import by.ita.je.dao.SharedDao;
import by.ita.je.model.Invite;
import by.ita.je.model.SharedList;
import by.ita.je.service.api.SharedService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SharedServiceImpl implements SharedService {
    private final SharedDao sharedDao;

    @Override
    public void createShared(SharedList sharedList){
        List<SharedList> list = sharedDao.readAllByToId(sharedList.getToId());
        if (sharedList.getToId() == sharedList.getFromId())
            throw new RuntimeException("Нельзя делиться с самим собой!");
        for (SharedList shard : list) {
            if (shard.getToId() == sharedList.getToId() && shard.getFromId() == sharedList.getFromId()
                    && shard.getToDoId() == sharedList.getToDoId())
                throw new RuntimeException("Вы уже поделились задачей!");
        }
        sharedDao.save(sharedList);
    }

    @Override
    public List<SharedList> sharedListsRead(Long toId){
        boolean read = false;
        return sharedDao.readAllByUserIdRead(toId, read);
    }

    @Override
    public List<SharedList> sharedListsChange(Long toId){
        boolean change = true;
        return sharedDao.readAllByUserIdChange(toId, change);
    }

    @Override
    public void delete(Long toDoId, Long toId){
        SharedList shared = sharedDao.readByToDoIdToId(toDoId, toId);
        sharedDao.deleteById(shared.getId());
    }
}