package app.entity;

import java.util.Objects;

public class Task {
    private int id;
    private String name;
    private String deadline;
    private int userId;

    public Task(String name, String data) {
        this.name = name;
        this.deadline = data;
    }

    public Task(String name) {
        this.name = name;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setUserId(int id){
        userId = id;
    }

    public int getUserId(){
        return userId;
    }

    public String getName(){
        return name;
    }

    public String getDeadline(){
        return deadline;
    }

    public int getId() { return id; }

    public void setId(int id){
        this.id = id;
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
        return Objects.hash(id, name, userId, deadline);
    }
}