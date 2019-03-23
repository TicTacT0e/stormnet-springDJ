package app.dao;

import app.entities.Assigment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigmentDao {

    List<Assigment> getAll();

    Assigment findById(int projectId, int employeeId);

    void save(Assigment assigment);

    void delete(Assigment assigment);

    void delete(int projectId, int employeeId);

    void edit(Assigment assigment);
}
