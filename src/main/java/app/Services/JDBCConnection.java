package app.Services;

import app.entities.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JDBCConnection {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${db.url}")
    public String URL;

    @Value("${db.username}")
    public String username;

    @Value("${db.password}")
    public String password;

    //private static Connection connection;

    public Database getDatabase() {
        System.out.println(driver);
        System.out.println(URL);
        System.out.println(username);
        System.out.println(password);

        return new Database(driver, URL, username, password);
    }
}
