package domain.dao;

import java.util.List;

public interface CommonDao<T> { // naming이 좀..

    boolean add(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(long id) throws Exception;
    T get(long id) throws Exception;
    List<T> getAll() throws Exception;
    int countAll() throws Exception;

}
