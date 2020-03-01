package by.beg.catalog.dao;

import by.beg.catalog.entity.User;

public interface UserDAO {

    boolean createUser(User user);

    User getUser(String email, String password);
}
