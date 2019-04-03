package app.dao.impl;

import app.dao.InvitationDao;
import app.entities.Invitation;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InvitationDaoImpl implements InvitationDao {

    private static final String GET_ALL =
            "SELECT * FROM timesheet_dev.Invitations";

    private static final String GET_FINDBYID =
            "SELECT * FROM timesheet_dev.Invitations WHERE employeeId=?";

    private static final String SAVE =
            "INSERT INTO timesheet_dev.Invitations (employeeId, companyId, email, " +
                    "invitationsCode, dateEnd, status) VALUE (?, ?, ?, ?, ?, ?)";

    private static final String DELETE =
            "DELETE FROM timesheet_dev.Invitations WHERE employeeId=?";

    private static final String UPDATE =
            "UPDATE timesheet_dev.Invitations SET companyId=?, email=?, invitationsCode=?, dateEnd=?, status=?" +
                    " WHERE employeeId=?";

    private static final String IS_EXISTS =
            "SELECT EXISTS (SELECT employeeId FROM timesheet_dev.Invitations " +
                    "WHERE employeeId=?)";

    @Autowired
    JDBCConnection jdbcConnection;

    private static final int ROW_EXISTS = 1;

    @Override
    public List<Invitation> getAll() {
        List<Invitation> invitationList = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            invitationList = invitationListMapper(resultSet);

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitationList;
    }

    @Override
    public Invitation findById(int employeeId) {
        Invitation invitation = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FINDBYID)) {
            preparedStatement.setInt(1, employeeId);
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
    public void save(Invitation invitation) {
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setInt(1, invitation.getEmployeeId());
            preparedStatement.setInt(2, invitation.getCompanyId());
            preparedStatement.setString(3, invitation.getEmail());
            preparedStatement.setString(4, invitation.getInvitationsCode());
            preparedStatement.setDate(5, invitation.getDateEnd());
            preparedStatement.setString(6, invitation.getStatus());
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException exception) {
            throw new EntityAlreadyExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Invitation invitation) {
        delete(invitation.getEmployeeId());
    }

    @Override
    public void delete(int employeeId) {
        if (isInvitationExists(employeeId)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Invitation invitation) {
        if (isInvitationExists(invitation.getEmployeeId())) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setInt(1, invitation.getCompanyId());
            preparedStatement.setString(2, invitation.getEmail());
            preparedStatement.setString(3, invitation.getInvitationsCode());
            preparedStatement.setDate(4, invitation.getDateEnd());
            preparedStatement.setString(5, invitation.getStatus());
            preparedStatement.setInt(6, invitation.getEmployeeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isInvitationExists(int employeeId) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_EXISTS)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == ROW_EXISTS) {
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

        invitation.setCompanyId(resultSet.getInt("companyId"));
        invitation.setEmployeeId(resultSet.getInt("employeeId"));
        invitation.setEmail(resultSet.getString("email"));
        invitation.setInvitationsCode(resultSet.getString("invitationsCode"));
        invitation.setDateEnd(resultSet.getDate("dateEnd"));
        invitation.setStatus(resultSet.getString("status"));

        return invitation;
    }
}