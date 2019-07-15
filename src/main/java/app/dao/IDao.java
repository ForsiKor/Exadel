package main.java.app.dao;

import java.util.Collection;

public interface IDao <T>{
    public Collection<T> getAll() throws Exception;
    public int getMaxId() throws Exception;
    public void create(T t) throws Exception;
    public void delete(String name) throws Exception;
}
