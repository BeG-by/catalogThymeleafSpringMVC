package by.beg.catalog.service;

import by.beg.catalog.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    boolean registration(User user);

    User authorization(User user);

    void logout (HttpSession session);

}
