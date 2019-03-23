package app.resources;

import app.dao.AssigmentDao;
import app.entities.Assigment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/assigment")
public class AssigmentResource {

    @Autowired
    AssigmentDao assigmentDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assigment> getAll() {
        return assigmentDao.getAll();
    }

    @GET
    @Path("/{projectId}/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Assigment get(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId
    ) {
        return assigmentDao.findById(projectId, employeeId);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Assigment assigment) {
        assigmentDao.save(assigment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{projectId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId,
            Assigment assigment
    ) {
        if (projectId != assigment.getProjectId() ||
                employeeId != assigment.getEmployeeId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        assigmentDao.edit(assigment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{projectId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId
    ) {
        assigmentDao.delete(projectId, employeeId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
