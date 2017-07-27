package ru.demetra.callrec.service;

import ru.demetra.callrec.model.User;

import java.util.List;

/**
 * Service class for {@link User}
 *
 * @author Vyacheslav Y.
 * @version 1.0
 */
public interface UserService {

    User getUserByLogin(String login);

    void changeUserPass(User user);

    List<User> getUsers();
}
