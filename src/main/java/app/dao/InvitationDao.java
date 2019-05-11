package app.dao;

import app.entities.Invitation;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InvitationDao {

    List<Invitation> getAll();

    Invitation findById(int employeeId);

    void save(Invitation invitation);

    void delete(Invitation invitation);

    void delete(int employeeId);

    void edit(Invitation invitation);
}
