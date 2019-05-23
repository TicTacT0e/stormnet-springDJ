package app.dao;

import app.entities.Employee;
import app.entities.Project;
import app.entities.ProjectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends BasicCrudDao<Project> {

    //Integer findByCompanyId(int companyId);

    Project findByAssignmentId(int id);

    Long countActualProjectTime(int id);

    List<Employee> getProjectTeam(int id);

    ProjectPage getProjectData(int id);
}
