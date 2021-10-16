package by.ita.je.service.api;

import by.ita.je.model.User;

public interface BusinessService {

    User create (User user);

    User read (Long id);

    User update (User user, Long id);

    void delete (Long id);
}