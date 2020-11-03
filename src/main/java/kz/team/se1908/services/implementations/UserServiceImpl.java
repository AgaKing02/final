package kz.team.se1908.services.implementations;


import kz.team.se1908.DTOS.LoginData;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.implementations.UserRepositoryImpl;
import kz.team.se1908.repositories.interfaces.UserRepository;
import kz.team.se1908.services.interfaces.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepo = new UserRepositoryImpl();

    public User getUserByID(long id) {
        return userRepo.getUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public void addUser(User user) {
        userRepo.add(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.update(user);
    }

    @Override
    public User checkUserExistence(LoginData userLoginData) {
        return userRepo.getUserByLoginData(userLoginData);
    }
}

