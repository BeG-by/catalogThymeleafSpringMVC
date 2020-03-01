package by.beg.catalog.service;

import by.beg.catalog.dao.UserDAO;
import by.beg.catalog.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public boolean registration(User user) {
        return userDAO.createUser(user);

    }

    @Override
    @Transactional
    public User authorization(User user) {
        return userDAO.getUser(user.getEmail(), user.getPassword());
    }


}
