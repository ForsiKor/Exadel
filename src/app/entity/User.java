package app.entity;

import app.store.Store;

public class User {
    private String name;
    private Store store;

    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}