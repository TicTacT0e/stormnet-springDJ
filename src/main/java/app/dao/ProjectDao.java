package app.dao;

import app.entities.Project;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ProjectDao {

    List<Project> getAll() throws SQLException, ClassNotFoundException;

    Project findById(int id);

    void save(Project project);

    void delete(Project project);

    void edit(Project project);
}
