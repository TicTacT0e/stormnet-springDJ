package app.dao.impl;

import app.entities.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Transactional
public class LogsDaoImpl extends BasicCrudDaoImpl<Log> {

    @Autowired
    SessionFactory sessionFactory;
    private Class<? extends Log> daoType;


    @Override
    public Log findById(int id) {
        Log entity = sessionFactory.getCurrentSession().get(daoType, id);
        if (entity == null) {
            throw new NoSuchElementException();
        }
        return entity;
    }

    @Override
    public List<Log> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from app.entities.Log");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Log entity = session.load(daoType, id);
        session.delete(entity);
    }

    @Override
    public void create(Log entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(Log entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(Log entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
}
