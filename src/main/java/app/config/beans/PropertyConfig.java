package app.config.beans;

import app.Services.JDBCService;
import app.Services.ProjectVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:project.properties")
public class PropertyConfig {

    @Bean
    public ProjectVersionService getProjectVersionService() {
        return new ProjectVersionService();
    }

    @Bean
    public JDBCService getJDBCConnection() {
        return new JDBCService();
    }

}
