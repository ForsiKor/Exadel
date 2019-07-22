package app.dao;

import app.entity.User;
import app.store.*;

import java.util.Collection;
import java.util.Map;

public class UserDaoImpl implements IDao<User>{
    UserStoreImpl store = new UserStoreImpl();

    @Override
    public Collection<User> getAll() {
        return store.getInfo().values();
    }

    @Override
    public User create(User user) {
        Map<Integer, User> map = store.getInfo();
        map.put(user.getId(),user);
        store.addInfo(map);
        return user;
    }


    @Override
    public boolean delete(String name) {
        Map<Integer, User> map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
        return true;
    }

    @Override
    public User get(String name) {
        return store.getInfo().get(name);
    }

    public int getMaxId() {
        return store.getMaxId();
    }
}
