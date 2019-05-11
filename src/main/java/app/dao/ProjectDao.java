package app.dao;

import app.entities.Project;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectDao {

    List<Project> getAll();

    Project findById(int id);

    void save(Project project);

    void delete(Project project);

    void delete(int id);

    void edit(Project project);
}
