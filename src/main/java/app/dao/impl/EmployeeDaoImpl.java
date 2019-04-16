package app.dao.impl;

import app.dao.EmployeeDao;
import app.entities.Employee;
import app.entities.Project;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private static List<Employee> employees = new LinkedList<>();

    static {
        employees.add(new Employee(0, "Marinka Mandarinka",
                "someMarinkaPhotoUrl", "marinka.mandarinka@gmail.com"));
        employees.add(new Employee(1, "Alynka Apelsynka",
                "someAlynkaPhotoUrl", "alynka.apelsynka@gmail.com"));
        employees.add(new Employee(2, "Agap Kryvolap",
                "someAgapPhotoUrl", "agap.kryvolap@gmail.com"));
    }

    @Override
    public synchronized List<Employee> getAll() {
        return employees;
    }

    @Override
    public synchronized Employee findById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public synchronized void save(Employee employee) {
        for (Employee savedEmployee : employees) {
            if (savedEmployee.getId() == employee.getId()) {
                throw new EntityAlreadyExistsException();
            }
        }
        employees.add(employee);
    }

    @Override
    public synchronized void delete(Employee retiringEmployee) {
        if (!employees.contains(retiringEmployee)) {
            throw new EntityNotFoundException();
        }
        employees.remove(retiringEmployee);
    }

    @Override
    public synchronized void delete(int id) {
        delete(findById(id));
    }

    @Override
    public synchronized void edit(Employee employee) {
        Employee oldEmployee = findById(employee.getId());
        int index = employees.indexOf(oldEmployee);
        employees.remove(oldEmployee);
        employees.add(index, employee);
    }

    @Override
    public synchronized void assignToProject(Employee employee,
                                             Project project) {
        employee.assignToProject(project);
    }
}
