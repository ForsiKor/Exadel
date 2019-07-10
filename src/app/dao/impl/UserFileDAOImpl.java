package app.dao.impl;

import app.dao.DaoUser;
import app.entity.User;
import app.store.Store;

import java.util.List;

public class UserFileDAOImpl implements DaoUser {
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        for (int i = 0; i < 0; i++) {
            Store.getListUser().get(i);
        }
        return null;
    }
    @Override
    public User insertUser(User user){
        Store.getListUser().add(user);
        return user;
    }
    @Override
    public User updateUser(User user){

        return user;
    }
    @Override
    public void deleteUser(User user){
        Store.getListUser().remove(user);
    }
}
