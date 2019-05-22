package app.dao.impl;

import app.dao.BasicCrudDao;
import app.dao.ProjectDao;
import app.entities.*;
import app.exceptions.EntityNotFoundException;
import org.hibernate.Session;
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
    private BasicCrudDao<Assignment> assignmentBasicCrudDao;
    @Autowired
    private ProjectDao logsProjectDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private SessionFactory sessionFactory;

    private static final String FIND_BY_ASSIGNMENT_ID_QUERY = "select id from Logs logs join Assignment assign on logs.assignmentId = assign.id";
    private static final String GET_PROJECT_TEAM_QUERY = "select employeeId from Assignment assign join Project proj on assign.projectId = proj.id where proj.id =:id";

//    @Override
//    public Project findByCompanyId(int companyId) {
//        Project project = sessionFactory.getCurrentSession().get(Project.class, companyId);
//        if (project == null) {
//            throw new EntityNotFoundException();
//        }
//        return project;
//    }

    @Override
    public Project findByAssignmentId(int id) {
        Project project = (Project) sessionFactory.getCurrentSession().createQuery(FIND_BY_ASSIGNMENT_ID_QUERY);
        return project;
    }

    public long countActualProjectTime(int id) {
        List<Assignment> assignments = assignmentBasicCrudDao.findAll();
        long sumLogs = 0;
        for (Assignment assign : assignments) {
            List<Log> logs = (List<Log>) logsProjectDao.findByAssignmentId(assign.getId());
            for (Log log : logs) {
                sumLogs += log.getTime();
            }
        }
        return sumLogs;
    }

    @Override
    public List<Employee> getProjectTeam(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_PROJECT_TEAM_QUERY);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Project> getProjectData(int companyId) {
        List<ProjectPage> projectPageList = new LinkedList<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from Project where companyId = " + companyId);
        List<Project> projects = query.getResultList();
        ProjectPage projectPage = null;
        for (int i = 0; i < projects.size(); i++) {
            projectPage.setProjectColor(projects.get(i).getColor());
            projectPage.setProjectName(projects.get(i).getName());
            projectPage.setProjectCode(projects.get(i).getCode());
            projectPage.setProjectStartDate(projects.get(i).getStartDate());
            projectPage.setTeam(getProjectTeam(i));
            projectPage.setProjectLoading(countActualProjectTime(i));
            projectPageList.add(projectPage);
        }
        return projects;
    }
}




