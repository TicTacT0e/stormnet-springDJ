package app.dao;

import app.entities.Company;
import app.entities.Employee;
import app.entities.Project;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyDao {

    List<Company> getAll();

    Company findById(int id);

    void save(Company company);

    void delete(Company company);

    void delete(int id);

    void edit(Company company);

    void addEmployeeToCompany(Company company, Employee employee);

    void addProjectToCompany(Company company, Project project);

    List<Employee> getCompanyEmployees(Company company);

    List<Project> getCompanyProjects(Company company);

}
