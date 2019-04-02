package app.dao.impl;

import app.Services.JDBCConnection;
import app.dao.AssignmentDao;
import app.entities.Assignment;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {

    @Autowired
    JDBCConnection jdbcConnection;

    private static final int ROW_EXISTS = 1;

    private static final String GET_ALL =
            "SELECT * FROM timesheet_dev.Assignment";
    private static final String GET =
            "SELECT * FROM timesheet_dev.Assignment "
                    + "WHERE projectId=? AND employeeId=?";
    private static final String INSERT =
            "INSERT INTO timesheet_dev.Assignment "
                    + "(projectId, employeeId, workLoadInMinutes) "
                    + "VALUE (?, ?, ?)";
    private static final String DELETE =
            "DELETE FROM timesheet_dev.Assignment "
                    + "WHERE projectId=? AND employeeId=?";
    private static final String UPDATE =
            "UPDATE timesheet_dev.Assignment "
                    + "SET workLoadInMinutes=? "
                    + "WHERE projectId=? AND employeeId=?";
    private static final String IS_EXISTS =
            "SELECT EXISTS " +
                    "(SELECT projectId, employeeId "
                    + "FROM timesheet_dev.Assignment"
                    + " WHERE projectId=? AND employeeId=?)";


    @Override
    public List<Assignment> getAll() {
        List<Assignment> assignmentList = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            assignmentList = assignmentListMapper(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return assignmentList;
    }

    @Override
    public Assignment findById(int projectId, int employeeId) {
        Assignment assignment = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(GET)) {
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (!resultSet.first()) {
                throw new EntityNotFoundException();
            }
            assignment = assigmentMapper(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return assignment;
    }

    @Override
    public void save(Assignment assignment) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(INSERT)) {
            preparedStatement.setInt(1, assignment.getProjectId());
            preparedStatement.setInt(2, assignment.getEmployeeId());
            preparedStatement.setInt(3, assignment.getWorkLoadInMinutes());
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException exception) {
            throw new EntityAlreadyExistsException();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int projectId, int employeeId) {
        if (isAssignmentExists(projectId, employeeId)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE)) {
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void edit(Assignment assignment) {
        if (isAssignmentExists(assignment.getProjectId(),
                assignment.getEmployeeId())) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, assignment.getWorkLoadInMinutes());
            preparedStatement.setInt(2, assignment.getProjectId());
            preparedStatement.setInt(3, assignment.getEmployeeId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private boolean isAssignmentExists(int projectId, int employeeId) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(IS_EXISTS)) {
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()
                    && resultSet.getInt(1) == ROW_EXISTS) {
                return false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private List<Assignment> assignmentListMapper(ResultSet resultSet)
            throws SQLException {
        List<Assignment> assignmentList = new LinkedList<>();
        Assignment assignment;
        while (resultSet.next()) {
            assignment = assigmentMapper(resultSet);
            assignmentList.add(assignment);
        }
        return assignmentList;
    }

    private Assignment assigmentMapper(ResultSet resultSet)
            throws SQLException {
        Assignment assignment = new Assignment();

        assignment.setProjectId(resultSet.getInt("projectId"));
        assignment.setEmployeeId(resultSet.getInt("employeeId"));
        assignment.setWorkLoadInMinutes(
                resultSet.getInt("workLoadInMinutes"));
        return assignment;
    }
}
