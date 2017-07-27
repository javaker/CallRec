package ru.demetra.callrec.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.demetra.callrec.model.User;

import java.util.List;

/**
 * @author Vyacheslav Y.
 * @version 2.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get user from MySQL DB by 'login'
     */
    @Override
    @SuppressWarnings("unchecked")
    public User getUserByLogin(String login) {

        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from User user where user.login = :loginCode";

        Query result = session.createQuery(hql)
                .setParameter("loginCode", login);

        User user = (User) result.uniqueResult();

        return user;
    }

    /**
     * Get all(!admin) users from MySQL DB
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from User where login!='admin'").list();
    }

    /**
     * Update user password into MySQL DB
     */
    @Override
    public void changeUserPass(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);

    }
}
