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
    private SessionFactory sessionFactory;

    private static final String FIND_BY_ASSIGNMENT_ID_QUERY = "select id from Logs logs join Assignment assign on logs.assignmentId = assign.id";
    private static final String GET_PROJECT_TEAM_QUERY = "select employeeId from Assignment assign join Project proj on assign.projectId = proj.id where proj.id =:id";

    @Override
    public Project findByAssignmentId(int id) {
        Project project = (Project) sessionFactory.getCurrentSession().createQuery(FIND_BY_ASSIGNMENT_ID_QUERY);
        return project;
    }

    @Override
    public Long countActualProjectTime(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT projectId, SUM(time) from Logs log join Assignment assign on log.assignmentId = assign.id where assign.projectId = " + id + "group by projectId");
        query.setParameter("id", id);
        return convertListToLong(query.getResultList());
    }

//        List<Assignment> assignments = assignmentBasicCrudDao.findAll();
//        long sumLogs = 0;
//        for (Assignment assign : assignments) {
//            List<Log> logs = (List<Log>) projectDao.findByAssignmentId(assign.getId());
//            for (Log log : logs) {
//                sumLogs += log.getTime();
//            }
//        }
//        return sumLogs;}

    @Override
    public List<Employee> getProjectTeam(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_PROJECT_TEAM_QUERY);
        query.setParameter("id", id);
        return query.getResultList();
    }

//    @Override
//    public Integer findByCompanyId(int companyId) {
//        Project project = sessionFactory.getCurrentSession().get(Project.class, companyId);
//        if (project == null) {
//            throw new EntityNotFoundException();
//        }
//        return project.getCompanyId();
//    }

    @Override
    public ProjectPage getProjectData(int companyId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Project where companyId = " + companyId);
        List<Project> projects = query.getResultList();
        ProjectPage projectPage = null;
        for (int i = 1; i < projects.size(); i++) {
            projectPage.setProjectColor(projects.get(i).getColor());
            projectPage.setProjectName(projects.get(i).getName());
            projectPage.setProjectCode(projects.get(i).getCode());
            projectPage.setProjectStartDate(projects.get(i).getStartDate());
            projectPage.setTeam(getProjectTeam(i));
            projectPage.setProjectLoading(countActualProjectTime(i));
        }
        return projectPage;
    }

    public long convertListToLong(List<Long> list) {
        long sumLogs = 0;
        for (Long l : list) {
            sumLogs += l;
        }
        return sumLogs;
    }
}




