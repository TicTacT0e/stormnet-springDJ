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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invitation get(@PathParam("id") int id) {
        return invitationDao.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Invitation invitation) {
        invitationDao.save(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id,
                         Invitation invitation) {
        if (id != invitation.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        invitationDao.edit(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        invitationDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
