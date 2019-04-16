package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/assignment")
public class AssignmentResource {

    @Autowired
    private BasicCrudDao<Assignment> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assignment> getAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{assignmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Assignment get(
            @PathParam("assignmentId") int assignmentId
    ) {
        return basicCrudDao.findById(assignmentId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Assignment assignment) {
        basicCrudDao.create(assignment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{assignmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("assignmentId") int assignmentId,
            Assignment assignment
    ) {
        if (assignmentId != assignment.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        basicCrudDao.update(assignment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{assignmentId}")
    public Response delete(
            @PathParam("assignmentId") int assignmentId
    ) {
        basicCrudDao.deleteById(assignmentId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{assignmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("assignmentId") int assignmentId,
            Assignment assignment
    ) {
        basicCrudDao.delete(assignment);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
