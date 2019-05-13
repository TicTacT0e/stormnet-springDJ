package app.dao.impl;

import app.dao.ProjectPageEditDao;
import app.entities.Employee;
import app.entities.Project;
import app.entities.ProjectEditPage;
import app.entities.Timesheet;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProjectEditPageDaoImpl implements ProjectPageEditDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ProjectEditPage getPageData(String projectName){
        int projectId = findByName(projectName);
        ProjectEditPage projectEditPage = new ProjectEditPage();
        projectEditPage.setProject(getProjectByName(projectName));
        projectEditPage.setTeam(getTeamFromProject(projectId));
        projectEditPage.setManHoursExpected(getManHoursExpected(projectId));
        projectEditPage.setCurrentlySpentManHours(getCurrentlySpentManHours(projectId));
        projectEditPage.setWorkload(getTimesheetsForProject(projectId));
        return projectEditPage;
    }

    @Override
    public Integer findByName(String name) {
        Project project = sessionFactory.getCurrentSession().get(Project.class, name);
        return project.getId();
    }

    public Project getProjectByName(String name) {
        Project project = sessionFactory.getCurrentSession().get(Project.class, name);
        return project;
    }

    public List<Employee> getTeamFromProject(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Employee as employee join Assignment as assignment where employee.id = assignment.employeeId having projectId = :projectId");
        query.setParameter("projectId", id);
        return query.getResultList();
    }

    public long getManHoursExpected(int id) {
        Project project = sessionFactory.getCurrentSession().get(Project.class, id);
        return project.getManHours();
    }

    public long getCurrentlySpentManHours(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select time from Logs as logs join Assignment as assignment where logs.assignmentId = assignment.id having projectId = :projectId");
        query.setParameter("projectId", id);
        return convertListHoursToLong(query.getResultList());
    }

    public List<Timesheet> getTimesheetsForProject(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Timesheet as timesheet join Assignment as assignment where timesheet.assignmentId = id having projectId = :projectId");
        query.setParameter("projectId", id);
        return query.getResultList();
    }

    public long convertListHoursToLong(List<Long> list) {
        long hours = 0;
        for (Long l: list) {
            hours += l;
        }
        return hours;
    }












    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }
}
