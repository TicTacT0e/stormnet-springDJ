package app.dao.impl;

import app.dao.AssigmentDao;
import app.entities.Assigment;
import app.entities.Employee;
import app.entities.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AssigmentDaoImpl implements AssigmentDao {

    @Value("${db-driver}")
    private String driver;
    @Value("${db-url}")
    private String url;
    @Value("${db-username}")
    private String username;
    @Value("${db-password}")
    private String password;

    private Connection connection = null;

    @Override
    public List<Assigment> getAll() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        try {
            connection = DriverManager
                    .getConnection(url, username, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        List<Assigment> assigmentList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM timesheet_dev.Assigment");
            assigmentList = assigmentListMapper(resultSet);
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return assigmentList;
    }

    @Override
    public Assigment findById(int projectId, int companyId) {
        return null;
    }

    @Override
    public void save(Assigment assigment) {

    }

    @Override
    public void delete(Project project, Employee employee) {

    }

    @Override
    public void delete(int projectId, int companyId) {

    }

    @Override
    public void edit(Assigment assigment) {

    }

    private List<Assigment> assigmentListMapper(ResultSet resultSet)
            throws SQLException {
        List<Assigment> assigmentList = new LinkedList<>();
        Assigment assigment;
        while (resultSet.next()) {
            assigment = assigmentMapper(resultSet);
            assigmentList.add(assigment);
        }
        return assigmentList;
    }

    private Assigment assigmentMapper(ResultSet resultSet)
        throws SQLException {
        Assigment assigment = new Assigment();
        assigment.setProjectId(resultSet.getInt("projectId"));
        assigment.setEmployeeId(resultSet.getInt("employeeId"));
        assigment.setWorkLoadInMinutes(resultSet.getInt("workLoadInMinutes"));
        return assigment;
    }
}
