package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Notification;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NotificationDaoImpl implements BasicCrudDao<Notification> {
    public static final String CREATE_NOTIFICATION = "INSERT INTO notification (createdAt, employeeId, status, title, description, link) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String FIND_ALL = "SELECT * FROM notification";
    public static final String FIND_BY_ID = "SELECT * FROM notification WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM notification WHERE id = ?";
    public static final String UPDATE = "UPDATE notification SET createdAt = ?, employeeId = ?, status = ?, title = ?, description = ?, link = ? WHERE id = ?";

    @Autowired
    JDBCConnection jdbcConnection;

    @Override
    public void create(Notification notification) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement((CREATE_NOTIFICATION), Statement.RETURN_GENERATED_KEYS)) {
            setValues(statement, notification);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Notification notification) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setValues(statement, notification);
            statement.setInt(7, notification.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Notification findById(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Notification notification = getNotification(resultSet);
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> findAll() {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Notification> notifications = new LinkedList<>();

            while (resultSet.next()) {
                notifications.add(getNotification(resultSet));
            }
            return notifications;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Notification notification) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, notification.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Notification getNotification(ResultSet resultSet) {
        try {
            return new Notification(
                    resultSet.getInt("id"),
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

    public void setValues(PreparedStatement statement, Notification notification) {
        try {
            statement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
            statement.setInt(2, notification.getEmployeeId());
            statement.setString(3, notification.getStatus());
            statement.setString(4, notification.getTitle());
            statement.setString(5, notification.getDescription());
            statement.setString(6, notification.getLink());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
