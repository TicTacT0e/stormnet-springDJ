package app.dao.impl;

import app.dao.BasicCrudDao;
import app.dao.TimesheetDao;
import app.entities.Timesheet;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class TimesheetDaoImpl implements BasicCrudDao<Timesheet> {

    private final String FIND_BY_ID = "select * from timesheet where id = ?";
    private final String FIND_ALL = "select * from timesheet";
    private final String ADD = "insert into timesheet (periodId, timesheetJson, status) values (?, ?, ?)";
    private final String DELETE = "delete from timesheet where id = ?";
    private final String UPDATE = "update timesheet set periodId = ?, timesheetJson = ?, status = ? where id = ?";

    @Autowired
    JDBCConnection jdbcConnection;


    @Override
    public Timesheet findById(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Timesheet(
                    resultSet.getInt("id"),
                    resultSet.getInt("periodId"),
                    resultSet.getString("timesheetJson"),
                    resultSet.getString("status")
            );

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<Timesheet> findAll() {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Timesheet> timesheets = new LinkedList<>();
            while (resultSet.next()) {
                timesheets.add(new Timesheet(
                        resultSet.getInt("id"),
                        resultSet.getInt("periodId"),
                        resultSet.getString("timesheetJson"),
                        resultSet.getString("status")
                ));
            }
            return timesheets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void create(Timesheet timesheet) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = getValues(ADD, timesheet, connection)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Timesheet entity) {
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Timesheet timesheet) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = getValues(UPDATE, timesheet, connection)) {
            preparedStatement.setInt(4, timesheet.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getValues(String query, Timesheet timesheet, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, timesheet.getPeriodId());
            preparedStatement.setString(2, timesheet.getTimesheetJson());
            preparedStatement.setString(3, timesheet.getStatus());
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }
}
