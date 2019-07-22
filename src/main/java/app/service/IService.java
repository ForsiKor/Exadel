package app.service;

import java.util.Collection;

public interface IService <T>{
    Collection<T> getAll();
    T add(T type);
    boolean delete(String name);
    T get(String name);
}
