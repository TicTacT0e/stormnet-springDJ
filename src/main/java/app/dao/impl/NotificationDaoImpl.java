package app.dao.impl;

import app.Services.JDBCConnection;
import app.dao.NotificationDao;
import app.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {
    public static final String CREATE_NOIFICATION = "INSERT INTO notification (id, createdAt, employeeId, status, title, description, link) VALUES(?, ?, ?, ?, ?, ?, ?)";
    public static final String FIND_ALL = "SELECT * FROM notification";
    public static final String FIND_BY_ID = "SELECT * FROM notification WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE * FROM notification WHERE id = ?";
    public static final String UPDATE = "UPDATE notification SET ? = ? WHERE id = ?";

    @Autowired
    JDBCConnection jdbcConnection;

    @Override
    public Notification create() {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NOIFICATION);
            //todo
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Notification findById(int id) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> findAll() {
        return null;
    }

    @Override
    public Notification update() {
        return null;
    }

    @Override
    public Notification delete(int id) {
        return null;
    }

}
