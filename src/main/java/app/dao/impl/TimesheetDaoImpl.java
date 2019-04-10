package app.dao.impl;

import app.dao.TimesheetDao;
import app.entities.Timesheet;
import app.services.JDBCConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;


public class TimesheetDaoImpl implements TimesheetDao {

    @Autowired
    JDBCConnection jdbcConnection;


    @Override
    public String findById(int id) {
        String query = "select * from timesheet where id = ?";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return findElementInDBCastToString(resultSet);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "object not found";
    }

    @Override
    public String findAll() {
        String query = "select * from timesheet";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                stringBuilder.append(findElementInDBCastToString(resultSet));
            }
            return String.valueOf(stringBuilder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "objects not found";
    }

    @Override
    public void add(Timesheet timesheet) {
        String query = "insert into timesheet (id, periodId, timesheetJson, status) values (?, ?, ?, ?)";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, timesheet.getId());
            preparedStatement.setInt(2, timesheet.getPeriodId());
            preparedStatement.setString(3, timesheet.getTimesheetJson());
            preparedStatement.setString(4, timesheet.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from timesheet where id = ?";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Timesheet timesheet) {
        String query = "update timesheet set periodId = ?, timesheetJson = ?, status = ? where id = ?";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(4, timesheet.getId());
            preparedStatement.setInt(1, timesheet.getPeriodId());
            preparedStatement.setString(2, timesheet.getTimesheetJson());
            preparedStatement.setString(3, timesheet.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public String findElementInDBCastToString(ResultSet resultSet) throws SQLException {
        int timesheetId = resultSet.getInt("id");
        int periodId = resultSet.getInt("periodId");
        String timesheetJson = resultSet.getString("timesheetJson");
        String status = resultSet.getString("status");
        Timesheet timesheet = new Timesheet(timesheetId, periodId, timesheetJson, status);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String js = null;
        try {
            js = ow.writeValueAsString(timesheet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return js;
    }
}
