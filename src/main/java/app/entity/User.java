package main.java.app.entity;

import java.util.*;

public class User {
    private String name;
    private int id;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof  User)) return false;
        User user = (User) o;
        return name.equals(user.name);
    }
}