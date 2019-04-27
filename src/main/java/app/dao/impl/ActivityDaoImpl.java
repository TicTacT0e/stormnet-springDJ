package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Activity;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ActivityDaoImpl implements BasicCrudDao<Activity> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Activity findById(int id) {
        Activity activity = sessionFactory.getCurrentSession()
                .get(Activity.class, id);
        if (activity == null) {
            throw new EntityNotFoundException();
        }
        return activity;
    }

    @Override
    public List<Activity> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from Activity ");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void create(Activity entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Activity entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void update(Activity entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }
}
