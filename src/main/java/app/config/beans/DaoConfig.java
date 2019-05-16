package app.config.beans;


import app.dao.BasicCrudDao;

import app.dao.impl.AssignmentDaoImpl;
import app.dao.impl.CompanyDaoImpl;
import app.dao.impl.EmployeeDaoImpl;
import app.dao.impl.InvitationDaoImpl;
import app.dao.impl.LogsDaoImpl;
import app.dao.impl.NotificationDaoImpl;
import app.dao.impl.ProjectDaoImpl;
import app.dao.impl.TimesheetDaoImpl;
import app.entities.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public BasicCrudDao<Company> getCompanyDao() {
        return new CompanyDaoImpl();
    }

    @Bean
    public BasicCrudDao<Employee> getEmployeeDao() {
        return new EmployeeDaoImpl();
    }

    @Bean
    public BasicCrudDao<Log> getLogsDao() {
        return new LogsDaoImpl();
    }

    @Bean
    public BasicCrudDao<Project> getProjectDao() {
        return new ProjectDaoImpl();
    }

    @Bean
    public BasicCrudDao<Invitation> getInvitationDao() {
        return new InvitationDaoImpl();
    }

    @Bean
    public BasicCrudDao<Assignment> getAssignmentDao() {
        return new AssignmentDaoImpl();
    }

    @Bean
    public BasicCrudDao<Notification> getNotificationDao() {
        return new NotificationDaoImpl();
    }

    @Bean
    public BasicCrudDao<Timesheet> getTimesheetDao() {
        return new TimesheetDaoImpl();
    }

}
