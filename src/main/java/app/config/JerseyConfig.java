package app.config;

import app.resources.CompanyResource;
import app.resources.EmployeeResource;
import app.resources.LogsResource;
import app.resources.ProjectResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/*")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CompanyResource.class);
        register(EmployeeResource.class);
        register(LogsResource.class);
        register(ProjectResource.class);
    }
}
