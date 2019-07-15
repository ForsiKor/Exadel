package main.java.app.dao;

import main.java.app.entity.User;
import main.java.app.store.Store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements IDao<User>{
    private Store store = new Store();

    @Override
    public List<User> getAll() throws Exception {
        return (List<User>) store.getInfoUser().values();
    }

    @Override
    public void create(User user) throws Exception {
        Map<String, User> map = new HashMap<>();
        map = store.getInfoUser();
        map.put(user.getName(), user);
        store.addInfoUser(map);
    }


    @Override
    public void delete(String name) throws Exception{
        Map<String, User> map = new HashMap<>();
        map = store.getInfoUser();
        map.remove(name);
        store.addInfoUser(map);
    }

    @Override
    public int getMaxId() throws Exception {
        return store.getMaxIdUser();
    }

    public User getUser(String name) throws Exception {
        return store.getInfoUser().get(name);
    }
}
