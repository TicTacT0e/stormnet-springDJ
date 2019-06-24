package app.dao.impl;

import app.dao.ProjectDao;
import app.dto.ProjectDto;
import app.entities.Employee;
import app.entities.Project;
import app.entities.ProjectPage;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class ProjectDaoImpl extends BasicCrudDaoImpl<Project>
        implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String GET_PROJECT_TEAM_QUERY =
            "select u.name, u.photoUrl from Users as u join "
                    + "Employees as empl on u.id = empl.userId join "
                    + "Assignments as assign on empl.id = assign.employeeId "
                    + "join Projects as proj on proj.id = assign.projectId "
                    + "where proj.id = ?";
    private static final String GET_PROJECTS_BY_COMPANYID =
            "from Project where companyId =:companyId";
    private static final String GET_PROJECTLOADING_QUERY =
            "SELECT projectId, SUM(time) from Logs log "
                    + "join Assignments assign on log.assignmentId = assign.id "
                    + "where assign.projectId = ? group by projectId";

    @Override
    public List<Double> getProjectLoading(int projectId) {
        Query query = sessionFactory.getCurrentSession().
                createSQLQuery(GET_PROJECTLOADING_QUERY);
        query.setParameter(1, projectId);
        return query.getResultList();
    }

    @Override
    public List<Employee> getProjectTeam(int projectId) {
        Query query = sessionFactory.getCurrentSession().
                createSQLQuery(GET_PROJECT_TEAM_QUERY);
        query.setParameter(1, projectId);
        return query.getResultList();
    }

    @Override
    public List<ProjectPage> getProjectData(int companyId) {
        Query query = sessionFactory.getCurrentSession().
                createQuery(GET_PROJECTS_BY_COMPANYID);
        query.setParameter("companyId", companyId);
        List<Project> projects = query.getResultList();
        List<ProjectPage> projectPage = new LinkedList<>();
        for (int i = 0; i < projects.size(); i++) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectColor(projects.get(i).getColor());
            projectDto.setProjectName(projects.get(i).getName());
            projectDto.setProjectCode(projects.get(i).getCode());
            projectDto.setTeam(getProjectTeam(projects.get(i).getId()));
            projectDto.setProjectStartDate(projects.get(i).getStartDate());
            projectDto.setProjectLoading(getProjectLoading(
                    projects.get(i).getId()));
            projectPage.add(i, projectDto);
        }
        return projectPage;
    }
}




