package kz.team.se1908.repositories.interfaces;


import kz.team.se1908.DTOS.LoginData;
import kz.team.se1908.models.User;

import java.util.List;

public interface UserRepository extends EntityRepository<User> {

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    User getUserByLoginData(LoginData loginData);


}
