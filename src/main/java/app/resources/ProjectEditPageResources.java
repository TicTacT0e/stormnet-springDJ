package app.resources;

import app.dao.ProjectPageEditDao;
import app.entities.ProjectEditPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/projects")
public class ProjectEditPageResources {

    @Autowired
    private ProjectPageEditDao<ProjectEditPage> projectPageEditDao;

    @GET
    @Path("/{projectName}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectEditPage get(
            @PathParam("projectName") String projectName
    ) {
        return projectPageEditDao.getPageData(projectName);
    }
}
