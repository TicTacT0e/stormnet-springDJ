package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Assignment;
import app.exceptions.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AssignmentDaoImpl implements BasicCrudDao<Assignment> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Assignment findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Assignment assignment = session.get(Assignment.class, id);
        if (assignment == null) {
            throw new EntityNotFoundException();
        }
        session.getTransaction().commit();
        session.close();
        return assignment;
    }

    @Override
    public List<Assignment> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query
                = session.createQuery("from Assignment");
        List<Assignment> assignments = query.list();
        session.getTransaction().commit();
        session.close();
        return assignments;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Assignment assignment = session.load(Assignment.class, id);
        session.delete(assignment);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void create(Assignment entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Assignment entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Assignment entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
}
