package app.resources;


import app.dao.LogDao;
import app.entities.Log;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;

@Component
@Path("/logs")
public class LogsResource {

    @Autowired
    private LogDao logsDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getAll() {
        return logsDao.findAll();
    }

    @GET
    @Path("/{logsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Log get(@PathParam("logsId") int logsId) {
        return logsDao.findById(logsId);
    }

    @GET
    @Path("/period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getPeriod(@QueryParam("periodFrom") Long periodFrom,
                               @QueryParam("periodTo") Long periodTo) {
        return logsDao.findByPeriod(periodFrom, periodTo);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Log> add(List<Log> logs) {
        logsDao.createLog(logs);
        return logsDao.findByDay();
    }

    @POST
    @Path("/hello")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Log> edit(Log logs) {
        if (logs.getId() == null) {
            logsDao.create(logs);
        } else {
            logsDao.update(logs);
        }
        return logsDao.findAll();
    }

   /* @PUT
    @Path("/{logsId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("logsId") int logsId, Log logs) {
        logsDao.update(logs);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }*/

    @DELETE
    @Path("/{logsId}")
    public Response delete(@PathParam("logsId") int logsId) {
        logsDao.deleteById(logsId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
