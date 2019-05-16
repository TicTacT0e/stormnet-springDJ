package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Invitation;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InvitationDaoImpl implements BasicCrudDao<Invitation> {

    private static final String GET_ALL =
            "SELECT * FROM timesheet_dev.Invitations";

    private static final String GET_FINDBYID =
            "SELECT * FROM timesheet_dev.Invitations WHERE id=?";

    private static final String SAVE =
            "INSERT INTO timesheet_dev.Invitations (partnerId, " +
                    "invitationsCode, dateEnd, status, id) " +
                    "VALUE (?, ?, ?, ?, ?)";

    private static final String DELETE =
            "DELETE FROM timesheet_dev.Invitations WHERE id=?";

    private static final String UPDATE =
            "UPDATE timesheet_dev.Invitations SET partnerId=?," +
                    " invitationsCode=?, dateEnd=?, status=? WHERE id=?";

    private static final String IS_EXISTS =
            "SELECT EXISTS (SELECT id FROM timesheet_dev.Invitations " +
                    "WHERE id=?)";

    @Autowired
    JDBCConnection jdbcConnection;

    private static final int ROW_EXISTS = 1;

    @Override
    public Invitation findById(int id) {
        Invitation invitation = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(GET_FINDBYID)) {
            preparedStatement.setInt(1, id);
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
    public List<Invitation> findAll() {
        List<Invitation> invitationList = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            invitationList = invitationListMapper(resultSet);

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitationList;
    }

    @Override
    public void deleteById(int id) {
        if (isInvitationExists(id)) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Invitation entity) {
        try (Connection connection = jdbcConnection.getConnection()) {
            createPreparedStatement(entity, connection, SAVE).executeUpdate();

        } catch (SQLIntegrityConstraintViolationException exception) {
            throw new EntityAlreadyExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement createPreparedStatement(
            Invitation invitation, Connection connection, String string
    ) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(string);
        preparedStatement.setInt(1, invitation.getPartnerId());
        preparedStatement.setString(2, invitation.getInvitationsCode());
        preparedStatement.setDate(3, invitation.getDateEnd());
        preparedStatement.setString(4, invitation.getStatus());
        preparedStatement.setInt(5, invitation.getId());
        return preparedStatement;
    }


    @Override
    public void delete(Invitation entity) {
        deleteById(entity.getId());
    }

    @Override
    public void update(Invitation entity) {
        if (isInvitationExists(entity.getId())) {
            throw new EntityNotFoundException();
        }
        try (Connection connection = jdbcConnection.getConnection()) {
            createPreparedStatement(entity, connection, UPDATE).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isInvitationExists(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(IS_EXISTS)) {
            preparedStatement.setInt(1, id);
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

        invitation.setId(resultSet.getInt("id"));
        invitation.setPartnerId(resultSet.getInt("partnerId"));
        invitation.setInvitationsCode(resultSet.getString("invitationsCode"));
        invitation.setDateEnd(resultSet.getDate("dateEnd"));
        invitation.setStatus(resultSet.getString("status"));

        return invitation;
    }
}