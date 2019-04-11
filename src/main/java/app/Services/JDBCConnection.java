package app.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JDBCConnection {

    private Connection connection=null;

    public JDBCConnection() {
    }

    public JDBCConnection(Connection connection) {
        this.connection = connection;
    }

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${db.url}")
    public String URL;

    @Value("${db.username}")
    public String username;

    @Value("${db.password}")
    public String password;


    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
