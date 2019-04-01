package app.dao.impl;

import app.Services.JDBCConnection;
import app.dao.NotificationDao;
import app.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {
    public static final String CREATE_NOTIFICATION = "INSERT INTO notification (id, createdAt, employeeId, status, title, description, link) VALUES(?, ?, ?, ?, ?, ?, ?)";
    public static final String FIND_ALL = "SELECT * FROM notification";
    public static final String FIND_BY_ID = "SELECT * FROM notification WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE * FROM notification WHERE id = ?";
    public static final String UPDATE = "UPDATE notification SET ? = ? WHERE id = ?";

    @Autowired
    JDBCConnection jdbcConnection;

    @Override
    public void create(Notification notification) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_NOTIFICATION);
            statement.setInt(1, notification.getId());
            statement.setDate(2, (Date) notification.getCreatedAt());
            statement.setInt(3, notification.getEmployeeId());
            statement.setString(4, notification.getStatus());
            statement.setString(5, notification.getTitle());
            statement.setString(6, notification.getDescription());
            statement.setString(7, notification.getLink());
            statement.execute();
            close(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Notification findById(int id) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Notification(
                    resultSet.getInt("id"),
                    resultSet.getDate("createdAt"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("status"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("link"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> findAll() {
        try {
            List<Notification> notifications = new LinkedList<>();
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notifications.add(new Notification(
                        resultSet.getInt("id"),
                        resultSet.getDate("createdAt"),
                        resultSet.getInt("employeeId"),
                        resultSet.getString("status"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("link")));
            }
            close(connection, statement, resultSet);
            return notifications;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Notification notification) {
    }

    @Override
    public void delete(int id) {
    }

    public void close(Connection connection, Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        close(connection, statement);
    }

}
