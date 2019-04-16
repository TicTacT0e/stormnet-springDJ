package app.dao.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static DBConnection ourInstance = new DBConnection();

    public static DBConnection getInstance() {
        return ourInstance;
    }

    private Connection connection = null;

    private DBConnection() {
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                InputStream inputStream = DBConnection.class
                        .getClassLoader()
                        .getResourceAsStream("/dbConnect.properties");
                Properties properties = new Properties();
                assert inputStream != null;
                properties.load(inputStream);
                String connectionUrl = properties.getProperty("connectionUrl");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(connectionUrl,
                        username, password);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
