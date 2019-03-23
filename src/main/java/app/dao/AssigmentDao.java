package app.dao;

import app.entities.Assigment;
import app.entities.Employee;
import app.entities.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigmentDao {

    List<Assigment> getAll();

    Assigment findById(int projectId, int companyId);

    void save(Assigment assigment);

    void delete(Project project, Employee employee);

    void delete(int projectId, int companyId);

    void edit(Assigment assigment);
}
