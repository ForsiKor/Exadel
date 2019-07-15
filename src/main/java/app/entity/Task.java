package main.java.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task {
    private int id;
    private String name;
    private Date deadline;
    private int userId;

    public Task(String name, Date deadline) {
        this.name = name;
        this.deadline = deadline;
    }
    public Task(String name, String deadLine) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(deadLine);
        this.deadline = date;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = Integer.parseInt(userId);
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                userId == task.userId &&
                Objects.equals(name, task.name) &&
                Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deadline, userId);
    }
}