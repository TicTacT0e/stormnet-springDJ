package app.dao;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasicCrudDao<T> {

    T findById(int id);

    List<T> findAll();

    void deleteById(int id);

    void delete(T entity);

    void create(T entity);

    void update(T entity);
}
