package app.config;

import app.Services.JDBCService;
import app.entities.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCConnection {

    @Autowired
    JDBCService jdbcService;

    //private Database database = jdbcService.getDatabase();

    private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(jdbcService.getDriver());
            connection = DriverManager.getConnection(jdbcService.getURL(), jdbcService.getUsername(), jdbcService.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
