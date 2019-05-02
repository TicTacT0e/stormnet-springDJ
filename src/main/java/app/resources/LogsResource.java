package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/logs")
public class LogsResource {

    @Autowired
    private BasicCrudDao<Logs> basicCrudDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getAll() {

        return basicCrudDao.findAll();
    }

    @GET
    @Path("/{logsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Logs get(@PathParam("logsId") int logsId) {
        return basicCrudDao.findById(logsId);
    }

/*    @GET
    @Path("/get/today")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getForToday() {
        return basicCrudDao.getLogFor(LogsNamespace.TODAY);
    }

    @GET
    @Path("/get/week")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getForThisWeek() {
        return basicCrudDao.getLogFor(LogsNamespace.THIS_WEEK);
    }

    @GET
    @Path("/get/month")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getForThisMonth() {
        return basicCrudDao.getLogFor(LogsNamespace.THIS_MONTH);
    }*/

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Logs logs) {
        basicCrudDao.create(logs);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{logsId}")
    public Response delete(@PathParam("logsId") int logsId) {
        basicCrudDao.deleteById(logsId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
