package app.resources;

import app.dao.BasicCrudDao;
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
    private BasicCrudDao<Invitation> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invitation> getAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{invitationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invitation get(@PathParam("invitationId") int invitationId) {
        return basicCrudDao.findById(invitationId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Invitation invitation) {
        basicCrudDao.create(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{invitationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("invitationId") int invitationId,
                         Invitation invitation) {
        if (invitationId != invitation.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        basicCrudDao.update(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{invitationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("invitationId") int invitationId) {
        basicCrudDao.deleteById(invitationId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
