package app.dao;

import app.entity.User;

import java.util.List;

public interface DaoUser {
    User getUser();
    List<User> getAllUsers();
    User insertUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
}
