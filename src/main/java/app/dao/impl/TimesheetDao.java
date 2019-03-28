package app.dao.impl;

import app.entities.Timesheet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.*;


public class TimesheetDao {
    private static final String url = "jdbc:mysql://localhost/timesheet_dev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;


    public static String findById(int id) {
        String query = "select * from timesheet where id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = createConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return findElementInDBCastToString(resultSet);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(preparedStatement, connection);
            closeResultSet(resultSet);
        }
        return "object not found";
    }

    public static String findAll() {
        String query = "select * from timesheet";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = createConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                stringBuilder.append(findElementInDBCastToString(resultSet));
            }
            return String.valueOf(stringBuilder);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(preparedStatement, connection);
            closeResultSet(resultSet);
        }
        return "objects not found";
    }

    public static void add(Timesheet timesheet) {
        String query = "insert into timesheet (id, periodId, timesheetJson, status) values (?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = createConnection().prepareStatement(query);
            preparedStatement.setInt(1, timesheet.getId());
            preparedStatement.setInt(2, timesheet.getPeriodId());
            preparedStatement.setString(3, timesheet.getTimesheetJson());
            preparedStatement.setString(4, timesheet.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(preparedStatement, connection);
            closeResultSet(resultSet);
        }
    }

    public static void delete(int id) {
        String query = "delete from timesheet where id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = createConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(preparedStatement, connection);
            closeResultSet(resultSet);
        }
    }

    public static void update(Timesheet timesheet) {
        String query = "update timesheet set periodId = ?, timesheetJson = ?, status = ? where id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = createConnection().prepareStatement(query);
            preparedStatement.setInt(4, timesheet.getId());
            preparedStatement.setInt(1, timesheet.getPeriodId());
            preparedStatement.setString(2, timesheet.getTimesheetJson());
            preparedStatement.setString(3, timesheet.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(preparedStatement, connection);
        }
    }

    public static Connection createConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void closeConnections(PreparedStatement preparedStatement, Connection connection) {
        try {
            preparedStatement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static String findElementInDBCastToString(ResultSet resultSet) throws SQLException {
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
