package services.interfaces;


import models.User;
import DTOS.LoginData;

import java.util.List;

public interface UserService {
    User getUserByID(long id);

    User getUserByUsername(String username);

    void addUser(User user);

    void updateUser(User user);

    User checkUserExistence(LoginData userLoginData);

    List<User> getAllUsers();
}
