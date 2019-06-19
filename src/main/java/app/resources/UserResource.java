package app.resources;

import app.dao.BasicCrudDao;
import app.entities.User;
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
@Path("/user")
public class UserResource {

    @Autowired
    private BasicCrudDao<User> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("userId") int userId) {
        return basicCrudDao.findById(userId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        basicCrudDao.create(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("userId") int assignmentId,
            User user
    ) {
        basicCrudDao.update(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response delete(
            @PathParam("userId") int userId) {
        basicCrudDao.deleteById(userId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
