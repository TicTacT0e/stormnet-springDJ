package app.dao;

import app.entities.Assignment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDao {

    List getAll();

    Assignment findById(int id);

    void save(Assignment assigment);

    void delete(int id);

    void edit(Assignment assigment);
}
