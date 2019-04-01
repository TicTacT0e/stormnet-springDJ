package app.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JDBCConnection {

    private Connection connection;

//    @Value("${jdbc.driver}")
    private String driver = "com.mysql.jdbc.Driver";

//    @Value("${db.url}")
    public String URL = "jdbc:mysql://localhost:3306/timesheet_dev";

//    @Value("${db.username}")
    public String username = "root";

//    @Value("${db.password}")
    public String password = "admin";


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
