package kz.team.se1908.services.interfaces;


import kz.team.se1908.DTOS.LoginData;
import kz.team.se1908.models.User;

public interface UserService {
    User getUserByID(long id);

    User getUserByUsername(String username);

    void addUser(User user);

    void updateUser(User user);

    User checkUserExistence(LoginData userLoginData);
}
