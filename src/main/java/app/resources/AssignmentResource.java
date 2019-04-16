package app.resources;

import app.dao.AssignmentDao;
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
    private AssignmentDao assignmentDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assignment> getAll() {
        return assignmentDao.getAll();
    }

    @GET
    @Path("/{assignmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Assignment get(
            @PathParam("assignmentId") int assignmentId
    ) {
        return assignmentDao.findById(assignmentId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Assignment assignment) {
        assignmentDao.save(assignment);
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
        assignmentDao.edit(assignment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{assignmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("assignmentId") int assignmentId
    ) {
        assignmentDao.delete(assignmentId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
