package app.service;

import app.dao.DaoTask;
import app.dao.DaoUser;
import app.entity.Task;
import app.entity.User;

import java.util.Date;

public class Service {
    private DaoUser daoUser;
    private DaoTask daoTask;

    public Service(DaoUser daoUser) {
        this.daoUser = daoUser;
    }
    public Service(DaoTask daoTask) {
        this.daoTask = daoTask;
    }

    public User saveUser(String username) {
        return daoUser.insertUser(new User(username));
    }

    public Task saveTask(int id, String name, Date deadline, User user){
        return daoTask.insertTask(new Task(id, name, deadline, user));
    }
}