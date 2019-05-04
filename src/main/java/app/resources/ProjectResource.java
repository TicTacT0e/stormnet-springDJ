package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/project")
public class ProjectResource {

    @Autowired
    private BasicCrudDao<Project> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> findAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project findById(@PathParam("id") int id) {
        return basicCrudDao.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Project project) {
        if (id != project.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        basicCrudDao.update(project);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Project project) {
        basicCrudDao.create(project);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id) {
        basicCrudDao.deleteById(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
