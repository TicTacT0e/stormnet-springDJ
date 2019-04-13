package app.dao.impl;

import app.entities.Logs;
import java.sql.*;
import java.util.Calendar;

public class LogsDaoImpl {
    private final String HOST = "jdbc:mysql://localhost:3306/logs";
    private final String USERNAME = "root";
    private final String PASSWORD = "Kraskovski K30197";
    private final String query = "select*from logs";
    PreparedStatement preparedStatement = null;

    public String getAll() {
        Logs logs = new Logs();
        try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD)) {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                logs.setId(resultSet.getInt(1));
                logs.setProject(resultSet.getInt(2));
                logs.setEmployee(resultSet.getInt(3));
                logs.setTime(resultSet.getInt(4));
                logs.setComment(resultSet.getString(5));
                logs.setDate(resultSet.getDate(6));
                System.out.println(logs.toString());
            }
            st.close();
        } catch (SQLException e) {
        }
        return logs.toString();
    }

    public void save(app.entities.Logs logs) {
        try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD)) {
            preparedStatement = connection.prepareStatement("INSERT INTO logs (projectId,employeeId," +
                    "time,comment,date) values (?,?,?,?,?)");
            preparedStatement.setInt(1, logs.getProject());
            preparedStatement.setInt(2, logs.getEmployee());
            preparedStatement.setInt(3, logs.getTime());
            preparedStatement.setString(4, logs.getComment());
            preparedStatement.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD)) {
            preparedStatement = connection.prepareStatement("delete from logs where id=?");
            preparedStatement.setInt(1, 9);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

