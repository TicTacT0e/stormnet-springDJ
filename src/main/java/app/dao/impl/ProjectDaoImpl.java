package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Project;
import app.exceptions.EntityNotFoundException;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ProjectDaoImpl implements BasicCrudDao<Project> {

    @Override
    public Project findById(int id) {
        Project project = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException();
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = (List<Project>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Project").list();
        return projects;
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
    public void create(Project entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Project entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Project entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}




