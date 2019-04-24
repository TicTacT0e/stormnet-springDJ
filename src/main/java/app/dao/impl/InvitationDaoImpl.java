package app.dao.impl;

import app.config.beans.HibernateSessionFactoryUtil;
import app.dao.BasicCrudDao;
import app.entities.Invitation;
import app.exceptions.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class InvitationDaoImpl implements BasicCrudDao<Invitation> {

    @Override
    public Invitation findById(int id) {
        Invitation invitation = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Invitation.class, id);
        if (invitation == null) {
            throw new EntityNotFoundException();
        }
        return invitation;
    }

    @Override
    public List<Invitation> findAll() {
        List<Invitation> invitationList = (List<Invitation>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("from Invitation").list();
        return invitationList;
    }

    @Override
    public void create(Invitation entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(findById(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Invitation entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Invitation entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}