package ru.demetra.callrec.service;


import org.springframework.transaction.annotation.Transactional;
import ru.demetra.callrec.dao.UserDao;
import ru.demetra.callrec.model.User;

import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Vyacheslav Y.
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return this.userDao.getUserByLogin(login);

    }

    @Override
    @Transactional
    public void changeUserPass(User user) {

        this.userDao.changeUserPass(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {

        return this.userDao.getUsers();
    }
}
