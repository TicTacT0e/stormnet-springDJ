package app.config;


import app.resources.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/*")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CompanyResource.class);
        register(EmployeeResource.class);
        register(LogsResource.class);
        register(ProjectResource.class);
        register(AssignmentResource.class);
        register(ProjectVersionResource.class);
        register(NotificationResource.class);
    }
}
