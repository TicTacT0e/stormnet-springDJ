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
    AssignmentDao assigmentDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assignment> getAll() {
        return assigmentDao.getAll();
    }

    @GET
    @Path("/{projectId}/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Assignment get(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId
    ) {
        return assigmentDao.findById(projectId, employeeId);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Assignment assignment) {
        assigmentDao.save(assignment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{projectId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId,
            Assignment assignment
    ) {
        if (projectId != assignment.getProjectId() ||
                employeeId != assignment.getEmployeeId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        assigmentDao.edit(assignment);
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
