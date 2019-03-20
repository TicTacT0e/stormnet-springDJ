package app.dao.impl;

import app.dao.CompanyDao;
import app.dao.database.DBConnection;
import app.entities.Company;
import app.entities.Employee;
import app.entities.Project;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        List<Company> _companies = null;
        try {
            Statement statement = DBConnection.getInstance()
                    .getConnection().createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM timesheet_dev.Company");
            _companies = getCompanies(resultSet);
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return _companies;
    }

    @Override
    public synchronized Company findById(int id) {
        Company company = null;
        try {
            PreparedStatement preparedStatement =
                    DBConnection.getInstance().getConnection()
                            .prepareStatement("SELECT * FORM timesheet_dev.Company " +
                                    "WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            company = mapping(resultSet);
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if(company == null) {
            throw new EntityNotFoundException();
        }
        return company;
    }

    @Override
    public synchronized void save(Company company) {
        try {
            PreparedStatement preparedStatement =
                    DBConnection.getInstance().getConnection()
                            .prepareStatement("INSERT INTO timesheet_dev.Company " +
                                    "(id, companyName, logoUrl) " +
                                    "VALUE (?, ?, ?)");
            preparedStatement.setInt(1, company.getId());
            preparedStatement.setString(2, company.getName());
            preparedStatement.setString(3, company.getLogoUrl());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public synchronized void delete(Company retiringCompany) {
        try {
            PreparedStatement preparedStatement =
                    DBConnection.getInstance().getConnection()
                            .prepareStatement("DELETE FROM timesheet_dev.Company " +
                                    "WHERE id=?");
            preparedStatement.setInt(1, retiringCompany.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public synchronized void delete(int id) {
        delete(findById(id));
    }

    @Override
    public synchronized void edit(Company company) {
        try {
            PreparedStatement preparedStatement =
                    DBConnection.getInstance().getConnection()
                            .prepareStatement("UPDATE timesheet_dev.Company " +
                                    "SET companyName=?, " +
                                    "logoUrl=?, " +
                                    "WHERE id=?");
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getLogoUrl());
            preparedStatement.setInt(3, company.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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

    private List<Company> getCompanies(ResultSet resultSet)
            throws SQLException {
        List<Company> _companies = new LinkedList<>();
        Company company;
        while (resultSet.next()) {
            company = mapping(resultSet);
            _companies.add(company);
        }
        return _companies;
    }

    private Company mapping(ResultSet resultSet)
            throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("companyName"));
        company.setLogoUrl(resultSet.getString("logoUrl"));
        return company;
    }
}
