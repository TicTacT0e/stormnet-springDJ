package app.entities;

public class Database {
    private String driver;
    private String URL;
    private String username;
    private String password;

    public Database() {
    }

    public Database(String driver, String URL, String username, String password) {
        this.driver = driver;
        this.URL = URL;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
