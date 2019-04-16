package app.dao.impl;

import app.dao.ProjectDao;
import app.entities.Project;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Repository
public class ProjectDaoImpl implements ProjectDao {

    private static final String GET_ALL = "SELECT * FROM timesheet_dev.project";
    private static final String FIND_BY_ID = "SELECT * FROM timesheet_dev.project WHERE id=?";
    private static final String SAVE = "INSERT INTO timesheet_dev.project(name, logoUrl, " +
            "startDate, endDate, manHours, code, color, description) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM timesheet_dev.project WHERE id=?";
    private static final String EDIT = "UPDATE timesheet_dev.project SET name=?, logoUrl=?, " +
            "startDate=?, endDate=?, manHours=?, code=?, color=?, description=? WHERE id=?";
    private static final String IS_EXIST = "SELECT EXISTS (SELECT id FROM timesheet_dev.project" +
            "WHERE id=?)";
    private static final int ROW_EXISTS = 1;

    @Autowired
    JDBCConnection jdbcConnection;

    @Override
    public synchronized List<Project> getAll() {
        List<Project> projectList = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            projectList = projectListMapper(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    @Override
    public synchronized Project findById(int id) {
        Project project = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            project = projectMapper(resultSet);

            if (project == null) {
                throw new EntityNotFoundException();
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public synchronized void save(Project project) {
        try (Connection connection = jdbcConnection.getConnection()) {
            createPreparedStatement(project, connection, SAVE).executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void edit(Project project) {
        if (isProjectExists(project.getId())) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT);
            preparedStatement.setString(1, project.getCode());
            preparedStatement.setInt(2, project.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void delete(Project project) {
        delete(project.getId());
    }

    public void delete(int id) {
        if (isProjectExists(id)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement createPreparedStatement(Project project, Connection connection, String command) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, project.getName());
        preparedStatement.setString(2, project.getLogoUrl());
        preparedStatement.setDate(3, project.getStartDate());
        preparedStatement.setDate(4, project.getEndDate());
        preparedStatement.setLong(5, project.getManHours());
        preparedStatement.setString(6, project.getCode());
        preparedStatement.setString(7, project.getColor());
        preparedStatement.setString(8, project.getDescription());
        return preparedStatement;
    }

    private List<Project> projectListMapper(ResultSet resultSet) throws SQLException {
        List<Project> projectList = new LinkedList<>();
        Project project;
        while (resultSet.next()) {
            project = projectMapper(resultSet);
            projectList.add(project);
        }
        return projectList;
    }

    private Project projectMapper(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setLogoUrl(resultSet.getString("logoUrl"));
        project.setStartDate(resultSet.getDate("startDate"));
        project.setEndDate(resultSet.getDate("endDate"));
        project.setManHours(resultSet.getLong("manHours"));
        project.setCode(resultSet.getString("code"));
        project.setColor(resultSet.getString("color"));
        project.setDescription(resultSet.getString("description"));
        return project;
    }

    private boolean isProjectExists(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == ROW_EXISTS) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}




