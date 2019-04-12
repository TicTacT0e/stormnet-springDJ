package app.dao;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasicCrudDao<T> {

    T findById(int id);

    List<T> getAll();

    void deleteById(int id);

    void edit(T entity);

    void update(T entity);
}
