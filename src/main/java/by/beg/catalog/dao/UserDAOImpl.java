package by.beg.catalog.dao;

import by.beg.catalog.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDAOImpl implements UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean createUser(User user) {

        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(email) FROM User WHERE email = :email");
        query.setParameter("email", user.getEmail());

        long count = (long) query.getSingleResult();

        if (count == 0) {
            sessionFactory.getCurrentSession().persist(user);
            logger.info("User was created: " + user);
            return true;
        }

        logger.info("User is existed: " + user);
        return false;
    }

    @Override
    public User getUser(String email, String password) {

        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE email = :email AND password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user = null;

        try {
            user = (User) query.getSingleResult();
            logger.info("User by email and password was found: " + user);
        } catch (NoResultException ex) {
            logger.info("User wasn't found");
        }

        return user;
    }
}
