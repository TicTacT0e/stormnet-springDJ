package app.dao.impl;

import app.dao.InvitationDao;
import app.entities.Invitation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InvitationDaoImpl implements InvitationDao {

    private static List<Invitation> invitations = new LinkedList<>();

    @Value("${db.DriverPath}")
    private String dbDriver;
    @Value("${db.Url}")
    private String dbUrl;
    @Value("${db.UserName}")
    private String dbUserName;
    @Value("${db.UserPassword}")
    private String dbUserPassword;

    private String tableName = "timeesheet_dev.Invitations";


    @Override
    public synchronized List<Invitation> getAll() {
        try (Connection connection = createDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            invitations.add((Invitation) resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitations;
    }

    @Override
    public synchronized Invitation findById(int employeeId, int companyId) {
        Invitation invitation = null;
        try (Connection connection = createDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName +
                     "where companyId = ? and employeeId = ?")) {
            preparedStatement.setInt(1, companyId);
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            while (resultSet.next()){

            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitation;
    }

    @Override
    public synchronized void save(Invitation invitation) {
        try (Connection connection = createDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into" + tableName +
                    "employeeId, companyId, email, invitationsCode, dateEnd, status" + "value (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, invitation.getEmployeeId());
            preparedStatement.setInt(2, invitation.getCompanyId());
            preparedStatement.setString(3, invitation.getEmail());
            preparedStatement.setString(4, invitation.getInvitationsCode());
            preparedStatement.setDate(5, (Date) invitation.getDateEnd());
            preparedStatement.setString(6, invitation.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void delete(Invitation invitation) {
        delete(invitation.getCompanyId(), invitation.getEmployeeId());
    }

    @Override
    public synchronized void delete(int companyId, int employeeId) {
        try (Connection connection = createDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from" +
                    tableName + "where employeeId = ? and companyId = ?");
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, companyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void edit(Invitation invitation) {
        try (Connection connection = createDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update" + tableName +
                    "set invitationsCode = ? where employeeId = ? and companyId = ?");
            preparedStatement.setString(1, invitation.getInvitationsCode());
            preparedStatement.setInt(2, invitation.getEmployeeId());
            preparedStatement.setInt(3, invitation.getCompanyId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection createDBConnection() {
        Connection connection = null;
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbUserPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
