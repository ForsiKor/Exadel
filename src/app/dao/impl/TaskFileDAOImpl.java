package app.dao.impl;

import app.dao.DaoTask;
import app.entity.Task;
import app.store.Store;

import java.util.List;

public class TaskFileDAOImpl implements DaoTask {
    @Override
    public Task getTask() {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return Store.getListTask();
    }

    @Override
    public Task insertTask(Task task) {
        Store.getListTask().add(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        Store.getListTask().remove(task);
    }
}
