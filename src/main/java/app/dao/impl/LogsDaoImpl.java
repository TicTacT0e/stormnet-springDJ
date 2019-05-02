package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Logs;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LogsDaoImpl implements BasicCrudDao<Logs> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Logs findById(int id) {
        Logs logs = sessionFactory.getCurrentSession().get(Logs.class, id);
        if (logs == null) {
            throw new EntityNotFoundException();
        }
        return logs;
    }

    @Override
    public List<Logs> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from app.entities.Logs");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public void create(Logs entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(Logs entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(Logs entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
}
