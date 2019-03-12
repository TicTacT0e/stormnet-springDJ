package app.config;

import app.service.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/*")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CompanyService.class);
        register(EmployeeService.class);
        register(LogsService.class);
        register(ProjectService.class);
        register(TestWeb.class);
    }
}
