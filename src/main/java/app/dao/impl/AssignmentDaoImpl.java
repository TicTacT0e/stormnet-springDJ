package app.dao.impl;

import app.dao.AssignmentDao;
import app.entities.Assignment;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AssignmentDaoImpl implements AssignmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Assignment> getAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from Assignment");
        return query.getResultList();
    }

    @Override
    public Assignment findById(int id) {
        return sessionFactory.getCurrentSession()
                .get(Assignment.class, id);
    }

    @Override
    public void save(Assignment assignment) {
        sessionFactory.getCurrentSession()
                .save(assignment);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void edit(Assignment assignment) {
        sessionFactory.getCurrentSession()
                .update(assignment);
    }
}
