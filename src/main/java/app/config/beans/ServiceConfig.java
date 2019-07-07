package app.config.beans;

import app.services.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public EmployeeService getEmployeeService() {
        return new EmployeeService();
    }
}
