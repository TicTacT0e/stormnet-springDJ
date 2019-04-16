package app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JDBCConnection {

    private Connection connection;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    /*private final String url = "jdbc:mysql://localhost:3306/logs";
    private final String username = "root";
    private final String password = "Kraskovski K30197";*/
    public Connection getConnection() {

            // Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

