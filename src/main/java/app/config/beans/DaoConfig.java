package app.config.beans;


import app.dao.CompanyDao;
import app.dao.EmployeeDao;
import app.dao.LogsDao;
import app.dao.ProjectDao;
import app.dao.impl.CompanyDaoImpl;
import app.dao.impl.EmployeeDaoImpl;
import app.dao.impl.ProjectDaoImpl;
import app.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:project.properties")
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

}
