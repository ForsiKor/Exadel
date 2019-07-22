package app.dao;

import java.util.Collection;

public interface IDao <T>{
     Collection<T> getAll();
     T get(String name);
     T create(T t);
     boolean delete(String name);
}
