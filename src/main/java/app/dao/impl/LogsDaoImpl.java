package app.dao.impl;

import app.dao.LogsDao;
import app.entities.Logs;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Repository
public class LogsDaoImpl implements LogsDao {

    private final String QUERY_GET_ALL = "select * from logs where date <= ? and date >= ?";
    private final String QUERY_SAVE = "INSERT INTO logs (assignmentId, order," +
            "time,comment,date) values (?,?,?,?,?)";
    private final String QUERY_DELETE = "delete from logs where id=?";

    @Autowired
    JDBCConnection jdbcconnection;

    public List<Logs> getAll(Date from, Date to) {
        List<Logs> logs = new LinkedList<>();
        try (Connection connection = jdbcconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();


            preparedStatement.setTimestamp(1,Timestamp.);
            preparedStatement.setDate(2, new Timestamp(new java.util.Date().setTime());


            while (resultSet.next()) {
                Logs log = new Logs();
                log.setId(resultSet.getInt(1));
                log.setAssignmentId(resultSet.getInt(2));
                log.setOrder(resultSet.getInt(3));
                log.setTime(resultSet.getDouble(4));
                log.setComment(resultSet.getString(5));
                log.setDate(resultSet.getDate(6));

                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(logs);
        return logs;
    }
    public static java.util.Date convertFromSQLDateToJAVADate(
            java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }
    @Override
    public List<Logs> getAll() {
        return new LinkedList<>();
    }

    @Override
    public void save(Logs logs) {
        try (Connection connection = jdbcconnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE);
            preparedStatement.setInt(1, logs.getAssignmentId());
            preparedStatement.setInt(2, logs.getOrder());
            preparedStatement.setDouble(3, logs.getTime());
            preparedStatement.setString(4, logs.getComment());
            preparedStatement.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = jdbcconnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


