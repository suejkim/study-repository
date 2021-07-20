package domain.dao;

import java.util.List;

public interface CommonDao<T> {

    boolean add(T t);
    boolean update(T t);
    boolean delete(long id);
    T get(long id);
    List<T> getAll();
    int countAll();

}
