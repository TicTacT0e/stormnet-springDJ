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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invitation> getAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invitation get(@PathParam("id") int id) {
        return (Invitation) basicCrudDao.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Invitation invitation) {
        basicCrudDao.create(invitation);
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
        basicCrudDao.update(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        basicCrudDao.deleteById(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
