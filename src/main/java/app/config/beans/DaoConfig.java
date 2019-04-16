package app.config.beans;



import app.dao.CompanyDao;
import app.dao.EmployeeDao;
import app.dao.LogsDao;
import app.dao.ProjectDao;
<<<<<<< HEAD
=======
import app.dao.impl.CompanyDaoImpl;
import app.dao.impl.EmployeeDaoImpl;
import app.dao.impl.ProjectDaoImpl;
import app.dao.*;
>>>>>>> 6a488d06e4074e7545d2b611da8dce9f58266dc6
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
<<<<<<< HEAD
    public AssignmentDao getAssignmentDao() {
        return new AssignmentDaoImpl();
    }

=======
    public InvitationDao getInvitationDao(){
        return new InvitationDaoImpl();
    }
  
    @Bean
    public AssignmentDao getAssignmentDao() {
        return new AssignmentDaoImpl();
    }
>>>>>>> 6a488d06e4074e7545d2b611da8dce9f58266dc6
}
