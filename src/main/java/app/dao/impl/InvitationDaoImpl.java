package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Invitation;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class InvitationDaoImpl implements BasicCrudDao<Invitation> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Invitation findById(int id) {
        Invitation invitation = sessionFactory.getCurrentSession()
                .get(Invitation.class, id);
        if (invitation == null) {
            throw new EntityNotFoundException();
        }
        return invitation;
    }

    @Override
    public List<Invitation> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from Invitation");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void create(Invitation entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Invitation entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void update(Invitation entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }
}