package app.resources;

import app.services.ProjectVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/get")
public class ProjectVersionResource {

    @Autowired
    ProjectVersionService projectVersionService;

    @GET
    @Path("/version")
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectVersion getProjectVersion() {
        return projectVersionService.getProjectVersion();
    }
}
