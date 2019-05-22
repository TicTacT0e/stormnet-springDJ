package app.dao;

import app.entities.Employee;
import app.entities.Project;
import app.entities.ProjectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends BasicCrudDao<Project> {
    //Project findByCompanyId(int companyId);

    Project findByAssignmentId(int id);

    long countActualProjectTime(int id);

    List<Employee> getProjectTeam(int id);

    List<Project> getProjectData(int companyId);
}
