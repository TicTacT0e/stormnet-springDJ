package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Project;
import app.exceptions.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ProjectDaoImpl implements BasicCrudDao<Project> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Project findById(int id) {
        Project project = sessionFactory.openSession().get(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException();
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = (List<Project>) sessionFactory.openSession().createQuery("from project");
        return projects;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(findById(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void create(Project entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Project entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Project entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}




