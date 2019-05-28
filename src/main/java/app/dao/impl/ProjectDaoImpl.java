package app.dao.impl;

import app.dao.ProjectDao;
import app.dto.ProjectDto;
import app.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class ProjectDaoImpl extends BasicCrudDaoImpl<Project> implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String GET_PROJECT_TEAM_QUERY = "select empl.name, empl.photoUrl from Employee empl join Assignment assign on empl.id = assign.employeeId join Project proj on proj.id = assign.projectId where proj.id =:id";
    private static final String GET_PROJECTS_BY_COMPANYID = "from Project where companyId =:companyId";
    private static final String GET_PROJECTLOADING_BY_ID = "SELECT projectId, SUM(time) from Logs log join Assignment assign on log.assignmentId = assign.id where assign.projectId =:id group by projectId";


    @Override
    public List<Double> getProjectLoading(int id) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_PROJECTLOADING_BY_ID);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Employee> getTeam(int id) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_PROJECT_TEAM_QUERY);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<ProjectPage> getProjectData(int companyId) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_PROJECTS_BY_COMPANYID);
        query.setParameter("companyId", companyId);
        List<Project> projects = query.getResultList();
        List<ProjectPage> projectPage = new LinkedList<>();
        for (int i = 0; i < projects.size(); i++) {
            ProjectDto projectDto = new ProjectDto();
            int c = 0;
            int d = i + 1;
            projectDto.setProjectColor(projects.get(i).getColor());
            projectDto.setProjectName(projects.get(i).getName());
            projectDto.setProjectCode(projects.get(i).getCode());
            projectDto.setTeam(getTeam(d));
            projectDto.setProjectStartDate(projects.get(i).getStartDate());
            projectDto.setProjectLoading(getProjectLoading(d));
            projectPage.add(c, projectDto);
        }
        return projectPage;
    }
}




