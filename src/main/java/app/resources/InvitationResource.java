package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Invitation;
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
@Path("/invitation")
public class InvitationResource {

    @Autowired
    private BasicCrudDao<Invitation> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invitation> findAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{invitationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invitation findById(@PathParam("invitationId") int invitationId) {
        return basicCrudDao.findById(invitationId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Invitation invitation) {
        basicCrudDao.create(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{invitationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("invitationId") int invitationId,
                         Invitation invitation) {
        if (invitationId != invitation.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode())
                    .build();
        }
        basicCrudDao.update(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{invitationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("invitationId") int invitationId) {
        basicCrudDao.deleteById(invitationId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteById(Invitation invitation) {
        basicCrudDao.delete(invitation);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
