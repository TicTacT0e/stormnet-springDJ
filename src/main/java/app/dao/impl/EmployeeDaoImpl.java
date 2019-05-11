package app.dao.impl;

import app.dao.EmployeeDao;
import app.entities.Employee;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Employee findById(int id) {
        Employee employee = sessionFactory.getCurrentSession()
                .get(Employee.class, id);
        if (employee == null) {
            throw new EntityNotFoundException();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from Employee");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void create(Employee entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Employee entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void update(Employee entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }
}
