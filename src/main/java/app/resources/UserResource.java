package app.resources;

import app.dao.impl.UserDaoImpl;
import app.dao.impl.ProjectDaoImpl;
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
    private UserDaoImpl userDao;
    @Autowired
    private ProjectDaoImpl projectDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("id") int id) {
        return userDao.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        userDao.create(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, User user) {
        userDao.update(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, User user) {
        userDao.delete(user);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/assign/{userId}/{projectId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignToProject(
            @PathParam("userId") int userId,
            @PathParam("projectId") int projectId) {
        userDao.assignToProject(userDao.findById(userId),
                projectDao.findById(projectId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
