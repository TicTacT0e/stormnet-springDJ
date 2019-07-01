package app.dao;

import app.entities.Employee;
import app.entities.Project;
import app.entities.ProjectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends BasicCrudDao<Project> {

    List<Double> getProjectLoading(int id);

    List<Employee> getProjectTeam(int id);

    List<ProjectPage> getProjectData(int id);
}
