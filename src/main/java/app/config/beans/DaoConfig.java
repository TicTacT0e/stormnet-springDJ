package app.config.beans;


import app.dao.CompanyDao;
import app.dao.EmployeeDao;
import app.dao.LogsDao;
import app.dao.ProjectDao;
import app.dao.impl.CompanyDaoImpl;
import app.dao.impl.EmployeeDaoImpl;
import app.dao.impl.ProjectDaoImpl;
import app.dao.impl.*;
import app.entities.Company;
import app.entities.Invitation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public BasicCrudDao<Company> getCompanyDao() {
        return new CompanyDaoImpl();
    }

    @Bean
    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImpl();
    }

    @Bean
    public LogsDao getLogsDao() {
        return new LogsDaoImpl();
    }

    @Bean
    public ProjectDao getProjectDao() {
        return new ProjectDaoImpl();
    }

    @Bean
    public BasicCrudDao<Invitation> getInvitationDao(){
        return new InvitationDaoImpl();
    }
  
    @Bean
    public AssignmentDao getAssignmentDao() {
        return new AssignmentDaoImpl();
    }

    @Bean
    public NotificationDaoImpl getNotificationDao() {
        return new NotificationDaoImpl();
    }

}
