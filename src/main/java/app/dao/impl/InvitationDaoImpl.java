package app.dao.impl;

import app.dao.InvitationDao;
import app.entities.Invitation;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InvitationDaoImpl implements InvitationDao {

    private static final String tableName = "timesheet_dev.Invitations";

    private static final String GET_ALL =
            "SELECT * FROM " + tableName;

    private static final String GET_FINDBYID =
            "SELECT * FROM " + tableName +
                    " WHERE companyId = ? " +
                    "AND employeeId = ?";

    private static final String SAVE =
            "INSERT INTO" + tableName +
                    " employeeId, " +
                    "companyId, " +
                    "email, " +
                    "invitationsCode, " +
                    "dateEnd, " +
                    "status " +
                    "VALUE (?, ?, ?, ?, ?, ?)";

    private static final String DELETE =
            "DELETE FROM " + tableName +
                    " WHERE employeeId = ? " +
                    "AND companyId = ?";

    private static final String UPDATE =
            "UPDATE " +
                    tableName +
                    " SET invitationsCode = ? " +
                    "WHERE employeeId = ? " +
                    "AND companyId = ?";

    private static final String IS_EXISTS =
            "SELECT EXISTS (SELECT companyId, employeeId FROM " + tableName
                    + " WHERE companyId=? " +
                    "AND employeeId=?)";


    @Autowired
    JDBCConnection jdbcConnection;

    private static final int ROW_EXISTS = 1;

    @Override
    public synchronized List<Invitation> getAll() {
        List<Invitation> invitationList = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            invitationList = invitationListMapper(resultSet);
//            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitationList;
    }

    @Override
    public synchronized Invitation findById(int employeeId, int companyId) {
        Invitation invitation = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FINDBYID)) {
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
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
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
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
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
                     .prepareStatement(IS_EXISTS)) {
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