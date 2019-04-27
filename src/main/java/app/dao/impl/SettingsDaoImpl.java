package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Settings;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SettingsDaoImpl implements BasicCrudDao<Settings> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Settings findById(int id) {
        Settings settings = sessionFactory.getCurrentSession()
                .get(Settings.class, id);
        if (settings == null) {
            throw new EntityNotFoundException();
        }
        return settings;
    }

    @Override
    public List<Settings> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from Settings ");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void create(Settings entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Settings entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void update(Settings entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }
}
