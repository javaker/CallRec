package ru.demetra.callrec.dao;

import ru.demetra.callrec.model.User;

import java.util.List;

/**
 * @author Vyacheslav Y.
 * @version 1.0
 */
public interface UserDao {

    User getUserByLogin(String login);

    List<User> getUsers();

    void changeUserPass(User user);

}
