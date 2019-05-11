package app.dao;

import app.entities.Employee;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeDao {

    List<Employee> findAll();

    Employee findById(int id);

    void create(Employee entity);

    void delete(Employee entity);

    void deleteById(int id);

    void update(Employee employee);
}
