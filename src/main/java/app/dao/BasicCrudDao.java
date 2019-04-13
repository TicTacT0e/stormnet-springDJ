package app.dao;

import org.springframework.stereotype.Repository;


@Repository
public interface BasicCrudDao<T> {

    T findById(int id);

    T getAll();

    void deleteById(int id);

    void deleteAll();

    void edit(T entity);

    void update(T entity);
}
