package app.Services;

import app.entities.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JDBCService {

    private Database database;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${db.url}")
    public String URL;

    @Value("${db.username}")
    public String username;

    @Value("${db.password}")
    public String password;

    public Database getDatabase() {
        System.out.println(driver);
        System.out.println(URL);
        System.out.println(username);
        System.out.println(password);

        if(this.database == null) {
            database = new Database(driver, URL, username, password);
            return database;
        }
        return database;
    }

    public String getDriver() {
        return driver;
    }

    public String getURL() {
        return URL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
