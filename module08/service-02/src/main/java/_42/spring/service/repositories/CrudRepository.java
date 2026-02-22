package _42.spring.service.repositories;

import java.util.*;

public interface CrudRepository<T> {

    T findById(long id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(long id);
    void cleanAll();
}
