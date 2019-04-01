package app.dao.impl;

import app.dao.InvitationDao;
import app.entities.Invitation;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InvitationDaoImpl implements InvitationDao {

    private static List<Invitation> invitationList = new LinkedList<>();

    private String tableName = "timeesheet_dev.Invitations";

    @Autowired
    JDBCConnection jdbcConnection;

    private static final int ROW_EXISTS = 1;

    @Override
    public synchronized List<Invitation> getAll() {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            invitationList = invitationListMapper(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitationList;
    }

    @Override
    public synchronized Invitation findById(int employeeId, int companyId) {
        Invitation invitation = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName +
                     "where companyId = ? and employeeId = ?")) {
            preparedStatement.setInt(1, companyId);
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            invitation = invitationMapper(resultSet);
            if (invitation == null) {
                throw new EntityNotFoundException();
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitation;
    }

    @Override
    public synchronized void save(Invitation invitation) {
        try (Connection connection = jdbcConnection.getConnection()) {
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
        if (isInvitationExists(companyId, employeeId)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
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
        if (isInvitationExists(invitation.getCompanyId(), invitation.getEmployeeId())) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
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

    private boolean isInvitationExists(int companyId, int employeeId) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT EXISTS"
                             + "(SELECT companyId, employeeId FROM "
                             + "timesheet_dev.Assignment "
                             + "WHERE projectId=? AND employeeId=?)")) {
            preparedStatement.setInt(1, companyId);
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()
                    && resultSet.getInt(1) == ROW_EXISTS) {
                return false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private List<Invitation> invitationListMapper(ResultSet resultSet)
            throws SQLException {
        List<Invitation> invitationList = new LinkedList<>();
        Invitation invitation;
        while (resultSet.next()) {
            invitation = invitationMapper(resultSet);
            invitationList.add(invitation);
        }
        return invitationList;
    }

    private Invitation invitationMapper(ResultSet resultSet)
            throws SQLException {
        Invitation invitation = new Invitation();
        if (!resultSet.wasNull()) {
            invitation.setCompanyId(resultSet.getInt("companyId"));
            invitation.setEmployeeId(resultSet.getInt("employeeId"));
            invitation.setEmail(resultSet.getString("email"));
            invitation.setInvitationsCode(resultSet.getString("invitationsCode"));
            invitation.setDateEnd(resultSet.getDate("dateEnd"));
            invitation.setStatus(resultSet.getString("status"));
        }
        return invitation;
    }
}