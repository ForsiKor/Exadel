package app.service;

import app.dao.IDao;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IService<User> {
    @Autowired
    private IDao<User> dao;

    @Override
    public User add(User user) {
        if (get(user.getName()) != null) {
            return null;
        }
        return dao.create(user);
    }
    @Override
    public boolean delete(String name) {
        if (dao.get(name) == null) {
            return false;
        }
        dao.delete(name);
        return true;
    }
    @Override
    public User get(String name) {
        return dao.get(name);
    }
    @Override
    public Collection<User> getAll() {
        return dao.getAll();
    }
}
