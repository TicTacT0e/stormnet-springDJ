package app.dao;

import app.entities.Assignment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDao {

    List<Assignment> getAll();

    Assignment findById(int assignmentId);

    void save(Assignment assigment);

    void delete(int assignmentId);

    void edit(Assignment assigment);
}
