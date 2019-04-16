package app.resources;


import app.dao.LogsDao;
import app.entities.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Component
@Path("/logs")
public class LogsResource {

    @Autowired
    private LogsDao logsDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getAll(@QueryParam("from") Date from, @QueryParam("to") Date to) {
        return logsDao.getAll(from, to);
    }
/*
    @GET
    @Path("/get/today")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getForToday() {
        return logsDao.getLogFor(LogsNamespace.TODAY);
    }

    @GET
    @Path("/get/week")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getForThisWeek() {
        return logsDao.getLogFor(LogsNamespace.THIS_WEEK);
    }

    @GET
    @Path("/get/month")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logs> getForThisMonth() {
        return logsDao.getLogFor(LogsNamespace.THIS_MONTH);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Logs logs) {
        logsDao.save(logs);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }*/

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        logsDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
