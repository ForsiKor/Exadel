package app.dao;

import app.entity.Task;

import java.util.List;

public interface DaoTask {
    Task getTask();
    List<Task> getAllTasks();
    Task insertTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Task task);
}
