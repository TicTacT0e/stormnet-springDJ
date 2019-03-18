package app.config.beans;


import app.service.*;
import app.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CompanyService getCompanyService() {
        return new CompanyServiceImpl();
    }

    @Bean
    public EmployeeService getEmployeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    public LogsService getLogsService() {
        return new LogsServiceImpl();
    }

    @Bean
    public ProjectService getProjectService() {
        return new ProjectServiceImpl();
    }

    @Bean
    public ProjectVersionService getProjectVersionService(){
        return new ProjectVersionServiceImpl();
    }

}

