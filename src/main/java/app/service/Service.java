package main.java.app.service;

import main.java.app.dao.*;
import main.java.app.entity.*;

import java.util.Collection;

public class Service {
    private UserDao userDao = new UserDao();
    private TaskDao taskDao = new TaskDao();

    public boolean addTask(Task task, String name) throws Exception{
        if (taskDao.getTask(task.getName()) != null) {
            return false;
        }
        if(userDao.getUser(name) == null) {
            User user = new User(name);
            user.setId(userDao.getMaxId());
            task.setUserId(user.getId());
            userDao.addUser(user);
        } else {
            task.setUserId(userDao.getUser(name).getId());
        }
        task.setUserId(taskDao.getMaxId());
        taskDao.addTask(task);
        return true;
    }

    public boolean addUser(User user) throws Exception{
        if(userDao.getUser(user.getName()) != null) {
            return false;
        }
        user.setId(userDao.getMaxId());
        userDao.addUser(user);
        return true;
    }

    public void deleteUser(String name) throws Exception{
        userDao.deleteUser(name);
    }

    public boolean deleteTask(String name) throws Exception {
        if(taskDao.getTask(name) == null) {
            return false;
        } else {
            taskDao.deleteTask(name);
            return true;
        }
    }


    public Task getTask(String name) throws Exception {
        return taskDao.getTask(name); }

    public User getUser(String name) throws Exception{
        return userDao.getUser(name); }

    public Collection<Task> getAllTasks() throws Exception {
        return taskDao.getAllTasks(); }

    public Collection<User> getAllUsers() throws Exception{
        return userDao.getAllUsers(); }

}