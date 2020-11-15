package repositories.interfaces;


import DTOS.LoginData;
import models.Group;
import models.User;
import repositories.interfaces.indirect.EntityRepository;

import java.util.List;

public interface UserRepository extends EntityRepository<User> {

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    User getUserByLoginData(LoginData loginData);

    List<User> getUsersByGroupLike(Group group);

    List<User> getUsersByName(String name);

    List<User> getUsersBySurname(String surname);




}
