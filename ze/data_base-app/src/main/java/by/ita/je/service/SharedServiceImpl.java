package by.ita.je.service;

import by.ita.je.dao.SharedDao;
import by.ita.je.model.SharedList;
import by.ita.je.service.api.SharedService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SharedServiceImpl implements SharedService {
    private final SharedDao sharedDao;

    @Override
    public void createShared(SharedList sharedList){
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
}