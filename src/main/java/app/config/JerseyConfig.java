package app.config;

import app.resources.AssignmentResource;
import app.resources.CompanyResource;
import app.resources.EmployeeResource;
import app.resources.InvitationResource;
import app.resources.LogsResource;
import app.resources.NotificationResource;
import app.resources.ProjectResource;
import app.resources.ProjectVersionResource;
import app.resources.SettingsResource;
import app.resources.TimesheetResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

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
        register(InvitationResource.class);
        register(SettingsResource.class);
        register(TimesheetResource.class);

        configureSwagger();
    }

    private void configureSwagger(){
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("spring-jersey-swagger-example");
        config.setTitle("Spring, Jersey, and Swagger Example");
        config.setVersion("1.0.0");
        config.setBasePath("/");
        config.setResourcePackage("app");
        config.setScan(true);

        SwaggerConfigLocator.getInstance()
                .putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, config);
    }
}
