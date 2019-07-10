package app.entity;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private Date deadline;
    private User user;

    public Task(int id, String name, Date deadline, User user) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public Date getDeadline() {
        return deadline;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
}