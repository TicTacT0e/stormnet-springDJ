package app.dao.impl;

import app.entities.Logs;
import java.sql.*;
import java.util.Calendar;

public class LogsDaoImpl {
    private final String HOST = "jdbc:mysql://localhost:3306/logs";
    private final String USERNAME = "root";
    private final String PASSWORD = "123";
    private Connection connection;
    PreparedStatement preparedStatement = null;

    public LogsDaoImpl() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO logs (projectId,employeeId,time,comment,date) " +
                    "values (?,?,?,?,?)");
            preparedStatement = connection.prepareStatement("delete from logs where id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAll() {
        Logs logs = new Logs();
        String query = "select*from logs";
        try {
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

    public void save() {
        try {
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 3);
            preparedStatement.setInt(3, 8);
            preparedStatement.setString(4, "Добавить рисунок");
            preparedStatement.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            preparedStatement.setInt(1, 2);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

