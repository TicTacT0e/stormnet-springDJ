package app.dao;

import app.entities.Employee;
import app.entities.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    List<Employee> getAll();

    Employee findById(int id);

    void save(Employee employee);

    void delete(Employee employee);

    void delete(int id);

    void edit(Employee employee);

    void assignToProject(Employee employee, Project project);
}
