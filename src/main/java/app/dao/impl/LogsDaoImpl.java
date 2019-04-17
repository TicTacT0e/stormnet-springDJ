package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Logs;
import app.entities.namespace.LogsNamespace;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Repository
@Transactional
public class LogsDaoImpl implements BasicCrudDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }
}
