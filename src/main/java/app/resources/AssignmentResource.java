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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assignment> getAll() {
        return assignmentDao.getAll();
    }

    @GET
    @Path("/{projectId}/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Assignment get(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId
    ) {
        return assignmentDao.findById(projectId, employeeId);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Assignment assignment) {
        assignmentDao.save(assignment);
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
        if (projectId != assignment.getProjectId()
                || employeeId != assignment.getEmployeeId()) {
            return Response
                    .status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        assignmentDao.edit(assignment);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{projectId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("projectId") int projectId,
            @PathParam("employeeId") int employeeId
    ) {
        assignmentDao.delete(projectId, employeeId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
