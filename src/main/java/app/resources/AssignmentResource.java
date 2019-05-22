package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Assignment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Operation(summary = "Find pet by ID",
            tags =
                    {
                            "Assignment"
                    },
            description = "Some description",
            responses =
                    {
                            @ApiResponse(description = "The pet", content = @Content(
                                    schema = @Schema(implementation = Assignment.class)
                            )),
                            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                            @ApiResponse(responseCode = "404", description = "Pet not found")
                    }
    )

    public Assignment get(
            @Parameter(
                    description = "ID",
                    schema = @Schema(
                            type = "integer",
                            format = "int64",
                            description = "param ID",
                            allowableValues = {"1", "2", "3"}
                    ),
                    required = true)
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
}
