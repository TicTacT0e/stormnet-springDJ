package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Integration;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class IntegrationDaoImpl implements BasicCrudDao<Integration> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integration findById(int id) {
        Integration integration = sessionFactory.getCurrentSession()
                .get(Integration.class, id);
        if (integration == null) {
            throw new EntityNotFoundException();
        }
        return integration;
    }

    @Override
    public List<Integration> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from Integration ");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void create(Integration entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Integration entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void update(Integration entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }
}
