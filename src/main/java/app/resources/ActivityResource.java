package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Activity;
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
@Path("/company/{companyId}/activity")
public class ActivityResource {

    @Autowired
    private BasicCrudDao<Activity> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getAll() {
        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{activityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity get(
            @PathParam("companyId") int companyId,
            @PathParam("activityId") int activityId
    ) {
        return basicCrudDao.findById(activityId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Activity activity) {
        basicCrudDao.create(activity);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{activityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("activityId") int activityId,
            Activity activity
    ) {
        basicCrudDao.update(activity);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{activityId}")
    public Response delete(
            @PathParam("activityId") int activityId
    ) {
        basicCrudDao.deleteById(activityId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
