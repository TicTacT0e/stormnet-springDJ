package app.resources;

import app.dao.RoleDao;
import app.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/role")
public class RoleResource {

    @Autowired
    private RoleDao roleDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @GET
    @Path("/code/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getByName(
            @PathParam("code") String code
    ) {
        return roleDao.findByCode(code);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Role role) {
        roleDao.create(role);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(
            @PathParam("code") String code
    ) {
        roleDao.deleteByCode(code);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
