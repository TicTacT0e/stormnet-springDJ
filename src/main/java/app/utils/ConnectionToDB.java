package app.utils;

import java.sql.*;

public class ConnectionToDB {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final String url = "jdbc:mysql://localhost/timesheet_dev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    public ConnectionToDB(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            this.preparedStatement = connection.prepareStatement(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnections(ConnectionToDB connectionToDB) {
        try {
            connectionToDB.preparedStatement.close();
            connectionToDB.connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void closeResultSet(ConnectionToDB connectionToDB){
        try {
            connectionToDB.resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResultSet(ConnectionToDB connectionToDB) throws SQLException {
        connectionToDB.resultSet = connectionToDB.preparedStatement.executeQuery();
        return connectionToDB.resultSet;
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }


}
