package app.dao;

import app.entities.Invitation;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface InvitationDao {

    List<Invitation> getAll() throws SQLException, ClassNotFoundException;

    Invitation findById(int projectId, int employeeId);

    void save(Invitation invitation);

    void delete(Invitation invitation);

    void delete(int companyId, int employeeId);

    void edit(Invitation invitation);
}
