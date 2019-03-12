package app.service;

import app.dao.ProjectDao;
import app.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/project")
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAll() {
        return projectDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project get(@PathParam("id") int id) {
        return projectDao.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Project project) {
        projectDao.save(project);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Project project) {
        projectDao.edit(project);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        projectDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
