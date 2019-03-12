package app.dao.impl;

import app.dao.CompanyDao;
import app.entities.Company;
import app.entities.Employee;
import app.entities.Project;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private static List<Company> companies = new LinkedList<>();

    static {
        companies.add(
                new Company(0, "Horn&Hooves", "someHorn&HoovesUrl"));
    }

    @Override
    public synchronized List<Company> getAll() {
        return companies;
    }

    @Override
    public synchronized Company findById(int id) {
        for (Company company : companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public synchronized void save(Company company) {
        for (Company savedCompany : companies) {
            if (savedCompany.getId() == company.getId()) {
                throw new EntityAlreadyExistsException();
            }
        }
        companies.add(company);
    }

    @Override
    public synchronized void delete(Company retiringCompany) {
        if (!companies.contains(retiringCompany)) {
            throw new EntityNotFoundException();
        }
        companies.remove(retiringCompany);
    }

    @Override
    public synchronized void delete(int id) {
        delete(findById(id));
    }

    @Override
    public synchronized void edit(Company company) {
        Company oldCompany = findById(company.getId());
        int index = companies.indexOf(oldCompany);
        companies.remove(oldCompany);
        companies.add(index, company);
    }

    @Override
    public synchronized void addEmployeeToCompany(Company company, Employee employee) {
        company.addEmployee(employee);
    }

    @Override
    public synchronized void addProjectToCompany(Company company, Project project) {
        company.addProject(project);
    }

    @Override
    public synchronized List<Employee> getCompanyEmployees(Company company) {
        return company.getEmployees();
    }

    @Override
    public synchronized List<Project> getCompanyProjects(Company company) {
        return company.getProjects();
    }
}
