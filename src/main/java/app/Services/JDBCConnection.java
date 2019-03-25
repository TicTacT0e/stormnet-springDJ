package app.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:project.properties")
public class JDBCConnection {

    @Value("${jdbc.driver}")
    private static String driver;

    @Value("${db.url}")
    public static String URL;

    @Value("${db.username}")
    public static String username;

    @Value("${db.password}")
    public static String password;

    private static Connection connection;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static Connection getConnection() {
        System.out.println(driver);
        System.out.println(URL);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
