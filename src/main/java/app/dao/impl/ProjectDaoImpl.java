package app.dao.impl;

import app.dao.ProjectDao;
import app.entities.Project;
import app.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    private String table = "timesheet_dev.project";
    private static List<Project> projects = new LinkedList<>();

    private Connection setConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public synchronized List<Project> getAll() {
        try (Connection connection = setConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            projects.add((Project) resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }


    @Override
    public synchronized Project findById(int id) {
        Project project = null;
        try (Connection connection = setConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table + "WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (project == null) {
            throw new EntityNotFoundException();
        } else {
            return project;
        }
    }

    @Override
    public synchronized void save(Project project) {
        try (Connection connection = setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO" + table + "id, logoUrl, startDate, endDate, manHours, code, colour, description"
                    + "value (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getLogoUrl());
            preparedStatement.setDate(4, (Date) project.getStartDate());
            preparedStatement.setDate(5, (Date) project.getEndDate());
            preparedStatement.setLong(6, project.getManHours());
            preparedStatement.setString(7, project.getCode());
            preparedStatement.setString(8, project.getColour());
            preparedStatement.setString(9, project.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void delete(Project project) {
        delete(project.getId());
    }

    public void delete(int id) {
        try (Connection connection = setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + "WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void edit(Project project) {
        try (Connection connection = setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE" + table + "SET code = ? WHERE id = ?");
            preparedStatement.setString(1, project.getCode());
            preparedStatement.setInt(2, project.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



