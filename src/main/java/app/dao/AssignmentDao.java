package app.dao;

import app.entities.Assignment;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface AssignmentDao {

    List<Assignment> getAll();

    Assignment findById(int projectId, int employeeId);

    void save(Assignment assigment);

    void delete(Assignment assigment);

    void delete(int projectId, int employeeId);

    void edit(Assignment assigment);
}
