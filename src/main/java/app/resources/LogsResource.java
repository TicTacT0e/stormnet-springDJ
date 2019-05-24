package app.resources;

import app.dao.BasicCrudDao;
import app.dao.LogDao;
import app.dao.impl.BasicCrudDaoImpl;
import app.entities.Log;
import app.entities.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
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

  /*  @GET
    @Path("/period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getPeriod(@RequestParam int periodFrom,
                               @RequestParam int periodTo) {

        return logsDao.findByPeriod(periodFrom, periodTo);
    }*/



    @GET
    @Path("/day")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getDay() {
        return logsDao.findByDay();
    }

    @GET
    @Path("/week")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getWeek() {
        return logsDao.findByWeek();
    }

    @GET
    @Path("/{periodFrom}/{periodTo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getPeriodFromTo(@QueryParam("periodFrom") Date periodFrom,
                                     @QueryParam("periodTo") Date periodTo) {

        return logsDao.findByPeriod(periodFrom, periodTo);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public  List<Log> add(List<Log> logs) {
        logsDao.createLog(logs);
        return logsDao.findByDay();
    }

    @DELETE
    @Path("/{logsId}")
    public Response delete(@PathParam("logsId") int logsId) {
        logsDao.deleteById(logsId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
