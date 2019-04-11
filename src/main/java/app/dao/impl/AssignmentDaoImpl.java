package app.dao.impl;

import app.dao.AssignmentDao;
import app.entities.Assignment;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
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
    private static final String GET_UNIQUE_ID =
            "SELECT * FROM timesheet_dev.Assignment "
                    + "WHERE id=?";
    private static final String GET =
            "SELECT * FROM timesheet_dev.Assignment "
                    + "WHERE projectId=? AND employeeId=?";
    private static final String INSERT =
            "INSERT INTO timesheet_dev.Assignment "
                    + "(id, activityId, workLoadInMinutes, projectId, employeeId) "
                    + "VALUE (?, ?, ?, ?, ?)";
    private static final String DELETE =
            "DELETE FROM timesheet_dev.Assignment "
                    + "WHERE projectId=? AND employeeId=?";
    private static final String DELETE_UNIQUE_ID =
            "DELETE FROM timesheet_dev.Assignment "
                    + "WHERE id=?";
    private static final String UPDATE =
            "UPDATE timesheet_dev.Assignment "
                    + "SET id=?, activityId=?, workLoadInMinutes=? "
                    + "WHERE projectId=? AND employeeId=?";
    private static final String IS_EXISTS_UNIQUE_ID =
            "SELECT EXISTS " +
                    "(SELECT id "
                    + "FROM timesheet_dev.Assignment"
                    + " WHERE id=?)";
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
    public Assignment findById(int assignmentId) {
        Assignment assignment = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(GET_UNIQUE_ID)) {
            preparedStatement.setInt(1, assignmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (isResultSetEmpty(resultSet)) {
                throw new EntityNotFoundException();
            }
            assignment = assigmentMapper(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return assignment;
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
            if (isResultSetEmpty(resultSet)) {
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
             PreparedStatement preparedStatement
                     = getPreparedStatementForUpdate(INSERT, assignment, connection)) {
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException exception) {
            throw new EntityAlreadyExistsException();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int assignmentId) {
        if (!isAssignmentExists(assignmentId)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatementForDelete(DELETE_UNIQUE_ID,
                     assignmentId,
                     connection)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int projectId, int employeeId) {
        if (!isAssignmentExists(projectId, employeeId)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatementForDelete(DELETE,
                     projectId,
                     employeeId,
                     connection)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void edit(Assignment assignment) {
        if (!isAssignmentExists(assignment.getId())) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatementForUpdate(UPDATE, assignment, connection)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private boolean isAssignmentExists(int assignmentId) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatementForDelete(IS_EXISTS_UNIQUE_ID,
                     assignmentId,
                     connection)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next()
                    && resultSet.getInt(1) == ROW_EXISTS;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private boolean isAssignmentExists(int projectId, int employeeId) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatementForDelete(IS_EXISTS,
                     projectId,
                     employeeId,
                     connection)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next()
                    && resultSet.getInt(1) == ROW_EXISTS;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private PreparedStatement getPreparedStatementForDelete(
            String query,
            int assignmentId,
            Connection connection
    ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, assignmentId);
        return preparedStatement;
    }

    private PreparedStatement getPreparedStatementForDelete(
            String query,
            int projectId,
            int employeeId,
            Connection connection
    ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, projectId);
        preparedStatement.setInt(2, employeeId);
        return preparedStatement;
    }

    private PreparedStatement getPreparedStatementForUpdate(
            String query,
            Assignment assignment,
            Connection connection
    ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, assignment.getId());
        preparedStatement.setInt(2, assignment.getActivityId());
        preparedStatement.setInt(3, assignment.getWorkLoadInMinutes());
        preparedStatement.setInt(4, assignment.getProjectId());
        preparedStatement.setInt(5, assignment.getEmployeeId());
        return preparedStatement;
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
        assignment.setId(resultSet.getInt("id"));
        assignment.setProjectId(resultSet.getInt("projectId"));
        assignment.setEmployeeId(resultSet.getInt("employeeId"));
        assignment.setActivityId(resultSet.getInt("activityId"));
        assignment.setWorkLoadInMinutes(resultSet.getInt("workLoadInMinutes"));
        return assignment;
    }

    private boolean isResultSetEmpty(ResultSet resultSet)
            throws SQLException {
        resultSet.next();
        return !resultSet.first();
    }
}
