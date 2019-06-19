package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Notification;
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
@Path("/notification")
public class NotificationResource {

    @Autowired
    private BasicCrudDao<Notification> notificationDao;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> getAll() {
        return notificationDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Notification getById(@PathParam("id") int id) {
        return notificationDao.findById(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Notification notification) {
        notificationDao.create(notification);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Notification notification) {
        notificationDao.update(notification);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id) {
        notificationDao.deleteById(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/")
    public Response delete(Notification notification) {
        notificationDao.delete(notification);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
