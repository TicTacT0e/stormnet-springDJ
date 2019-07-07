package app.config.beans;

import app.services.EmployeeService;
import app.services.PdfService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PdfService getPdfService() {
        return new PdfService();
    }

    @Bean
    public EmployeeService getEmployeeService() {
        return new EmployeeService();
    }
}
