package by.beg.catalog.service;

import by.beg.catalog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserService {

    private ArrayList<User> userList;

    @Autowired
    public UserService(@Qualifier("userList") ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public boolean tryRegistration(User user){

        for (User currentUser: userList) {
            if(currentUser.getEmail().equals(user.getEmail())){
                return false;
            }
        }

        userList.add(user);
        return true;
    }

    public User tryAuthorization(User user) {

        for (User currentUser : userList) {
            if (currentUser.equals(user)) {
                return currentUser;
            }
        }

        return null;

    }


}
