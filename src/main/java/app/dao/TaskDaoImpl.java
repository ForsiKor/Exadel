package app.dao;

import app.entity.Task;
import app.store.TaskStoreImpl;

import java.util.Collection;
import java.util.Map;

public class TaskDaoImpl implements IDao<Task> {
    TaskStoreImpl store = new TaskStoreImpl();

    @Override
    public Collection<Task> getAll() {
        return store.getInfo().values();
    }

    @Override
    public Task get(String name) {
        return store.getInfo().get(name);
    }


    @Override
    public Task create(Task task) {
        Map<Integer, Task> map = store.getInfo();
        map.put(task.getId(),task);
        store.addInfo(map);
        return task;
    }

    @Override
    public boolean delete(String name) {
        Map<Integer, Task> map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
        return true;
    }

    public int getMaxId() {
        return store.getMaxId();
    }

}
