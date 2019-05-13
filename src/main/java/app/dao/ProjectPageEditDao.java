package app.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPageEditDao<T> extends BasicCrudDao {

    T getPageData(String pageName);

    T findByName(String name);
}
