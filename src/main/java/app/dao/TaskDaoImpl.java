package main.java.app.dao;

import main.java.app.entity.Task;
import main.java.app.store.Store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDaoImpl implements IDao<Task> {
    private Store store = new Store();

    @Override
    public List<Task> getAll() throws Exception {
        return (List<Task>) store.getInfoTask().values();
    }

    @Override
    public void create(Task task) throws Exception {
        Map<String, Task> map = new HashMap<>();
        map = store.getInfoTask();
        map.put(task.getName(), task);
        store.addInfo(map);
    }


    @Override
    public void delete(String name) throws Exception{
        Map<String, Task> map = new HashMap();
        map = store.getInfoTask();
        map.remove(name);
        store.addInfo(map);
    }

    @Override
    public int getMaxId() throws Exception {
        return store.getMaxIdUser();
    }

    public Task getTask(String name) throws Exception {
        return store.getInfoTask().get(name);
    }
}
