package app.config.beans;


import app.dao.*;
import app.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public CompanyDao getCompanyDao() {
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
    public InvitationDao getInvitationDao(){
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
