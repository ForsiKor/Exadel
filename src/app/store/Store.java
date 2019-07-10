package app.store;

import app.entity.Task;
import app.entity.User;

import java.util.ArrayList;

public class Store {
    private ArrayList<User> listUser = new ArrayList<>();
    private ArrayList<Task> listTask = new ArrayList<>();

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public ArrayList<Task> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<Task> listTask) {
        this.listTask = listTask;
    }
}
