package by.ita.je.service.api;

import by.ita.je.model.SharedList;

import java.util.List;

public interface SharedService {
    void createShared(SharedList sharedList);

    List<SharedList> sharedListsRead(Long toId);

    List<SharedList> sharedListsChange(Long toId);
}