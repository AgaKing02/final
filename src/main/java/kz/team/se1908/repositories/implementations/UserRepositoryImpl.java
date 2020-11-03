package kz.team.se1908.repositories.implementations;

import kz.team.se1908.DTOS.LoginData;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.interfaces.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAllUsers() {
      return   query("SELECT * FROM users");
    }

    @Override
    public User getUserById(long id) {
        return queryOne("SELECT * FROM users WHERE id="+id+"LIMIT 1");
    }

    @Override
    public User getUserByUsername(String username) {
        return queryOne("SELECT * FROM user WHERE username='"+username+"' LIMIT 1");
    }

    @Override
    public User getUserByLoginData(LoginData loginData) {
       return queryOne("SELECT * FROM users WHERE username='"+loginData.getUsername()+"' AND password='"+loginData.getPassword()+"' LIMIT 1");
    }

    @Override
    public void add(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public List<User> query(String sql) {
        return null;
    }

    @Override
    public User queryOne(String sql) {
        return null;
    }
}
