package by.beg.catalog.service;

import by.beg.catalog.entity.User;

public interface UserService {

    boolean registration(User user);

    User authorization(User user);

}
