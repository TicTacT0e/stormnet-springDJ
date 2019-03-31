package app.resources;

import app.dao.InvitationDao;
import app.entities.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Component
@Path("/invitation")
public class InvitationResource {

    @Autowired
    InvitationDao invitationDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invitation> getAll() throws SQLException, ClassNotFoundException {
        return invitationDao.getAll();
    }

    @GET
    @Path("/{companyId}/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invitation get(@PathParam("companyId") int companyId, @PathParam("employeeId") int employeeId){
        return invitationDao.findById(companyId, employeeId);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Invitation invitation){
        invitationDao.save(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{companyId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(Invitation invitation){
        invitationDao.edit(invitation);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{companyId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("companyId") int companyId, @PathParam("employeeId") int employeeId){
        invitationDao.delete(companyId, employeeId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
