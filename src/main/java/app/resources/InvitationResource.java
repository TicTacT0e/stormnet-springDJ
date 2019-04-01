package app.resources;

import app.dao.InvitationDao;
import app.entities.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/invitation")
public class InvitationResource {

    @Autowired
    private InvitationDao invitationDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invitation> getAll() {
        return invitationDao.getAll();
    }

    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invitation get(@PathParam("employeeId") int employeeId) {
        return invitationDao.findById(employeeId);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Invitation invitation) {
        invitationDao.save(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("employeeId") int employeeId,
                         Invitation invitation) {
        if (employeeId != invitation.getEmployeeId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        invitationDao.edit(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("employeeId") int employeeId) {
        invitationDao.delete(employeeId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
