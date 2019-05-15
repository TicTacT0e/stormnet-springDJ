package app.dao;

import app.entities.Employee;
import app.entities.Project;
import app.entities.ProjectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends BasicCrudDao<Project> {
    Project findByAssignmentId(int id);

    long countActualProjectTime();

    List<Employee> getProjectTeam();

    List<ProjectPage> getProjectData();
}
