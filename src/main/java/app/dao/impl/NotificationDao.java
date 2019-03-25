package app.dao.impl;

import app.config.JDBCConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class NotificationDao {



    public String getAll() {
        try {
            Connection connection = new JDBCConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM NOTIFICATION");
            while (resultSet.next()) {
                System.out.println("IN CYCLE");
                return (resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("AND OF METHOD");
        return "END";
    }



/*    public String getAll(){
        try {
            Connection connection = JDBCConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return "String";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Exception";
        }
    }*/








}
