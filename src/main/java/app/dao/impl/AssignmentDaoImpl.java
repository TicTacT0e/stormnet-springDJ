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
    private static final String GET =
            "SELECT * FROM timesheet_dev.Assignment "
                    + "WHERE id=?";
    private static final String INSERT =
            "INSERT INTO timesheet_dev.Assignment "
                    + "(projectId, employeeId, activityId, workLoadInMinutes, id) "
                    + "VALUE (?, ?, ?, ?, ?)";
    private static final String DELETE =
            "DELETE FROM timesheet_dev.Assignment "
                    + "WHERE id=?";
    private static final String UPDATE =
            "UPDATE timesheet_dev.Assignment "
                    + "SET projectId=?, employeeId=?, activityId=?, workLoadInMinutes=? "
                    + "WHERE id=?";
    private static final String IS_EXISTS =
            "SELECT EXISTS " +
                    "(SELECT id "
                    + "FROM timesheet_dev.Assignment"
                    + " WHERE id=?)";

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
    public Assignment findById(int id) {
        Assignment assignment = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatement(GET, id, connection)) {
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
    public void delete(int id) {
        if (!isAssignmentExists(id)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatement(DELETE,
                     id,
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

    private boolean isAssignmentExists(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement
                     = getPreparedStatement(IS_EXISTS,
                     id,
                     connection)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next()
                    && resultSet.getInt(1) == ROW_EXISTS;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private PreparedStatement getPreparedStatement(
            String query,
            int assignmentId,
            Connection connection
    ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, assignmentId);
        return preparedStatement;
    }

    private PreparedStatement getPreparedStatementForUpdate(
            String query,
            Assignment assignment,
            Connection connection
    ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, assignment.getProjectId());
        preparedStatement.setInt(2, assignment.getEmployeeId());
        preparedStatement.setInt(3, assignment.getActivityId());
        preparedStatement.setInt(4, assignment.getWorkLoadInMinutes());
        preparedStatement.setInt(5, assignment.getId());
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
