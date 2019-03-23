package app.dao.impl;

import app.dao.AssigmentDao;
import app.entities.Assigment;
import app.exceptions.EntityNotFoundException;
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

    private void openDatabaseConnection() {
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
    }

    @Override
    public List<Assigment> getAll() {
        openDatabaseConnection();
        List<Assigment> assigmentList = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM timesheet_dev.Assigment");
            ResultSet resultSet = preparedStatement.executeQuery();
            assigmentList = assigmentListMapper(resultSet);
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return assigmentList;
    }

    @Override
    public Assigment findById(int projectId, int employeeId) {
        openDatabaseConnection();
        Assigment assigment = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM timesheet_dev.Assigment " +
                            "WHERE projectId=? AND employeeId=?");
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            assigment = assigmentMapper(resultSet);
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (assigment == null) {
            throw new EntityNotFoundException();
        } else {
            return assigment;
        }
    }

    @Override
    public void save(Assigment assigment) {
        openDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO timesheet_dev.Assigment " +
                            "(projectId, employeeId, workLoadInMinutes) " +
                            "VALUE (?, ?, ?)");
            preparedStatement.setInt(1, assigment.getProjectId());
            preparedStatement.setInt(2, assigment.getEmployeeId());
            preparedStatement.setInt(3, assigment.getWorkLoadInMinutes());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Assigment assigment) {
        delete(assigment.getProjectId(), assigment.getEmployeeId());
    }

    @Override
    public void delete(int projectId, int employeeId) {
        openDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM timesheet_dev.Assigment " +
                            "WHERE projectId=? AND employeeId=?");
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void edit(Assigment assigment) {
        openDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE timesheet_dev.Assigment " +
                            "SET workLoadInMinutes=? " +
                            "WHERE projectId=? AND employeeId=?");
            preparedStatement.setInt(1, assigment.getWorkLoadInMinutes());
            preparedStatement.setInt(2, assigment.getProjectId());
            preparedStatement.setInt(3, assigment.getEmployeeId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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
