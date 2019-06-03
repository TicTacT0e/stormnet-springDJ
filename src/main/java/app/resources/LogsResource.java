package app.resources;

import app.dao.LogDao;
import app.entities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import java.sql.Timestamp;
import java.util.List;

@Component
@Path("/logs")
public class LogsResource {

    @Autowired
    private LogDao logsDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getPeriod(@QueryParam("periodFrom") Timestamp periodFrom,
                               @QueryParam("periodTo") Timestamp periodTo) {
        return logsDao.findByPeriod(periodFrom, periodTo);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Log> edit(List<Log> logList) {
        for (Log entity : logList) {
            if (entity.getId() == null) {
                logsDao.createLog(logList);
            } else {
                logsDao.updateLog(logList);
            }
        }
        return logsDao.findByDay();
    }

    @DELETE
    @Path("/{logsId}")
    public Response delete(@PathParam("logsId") int logsId) {
        logsDao.deleteById(logsId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
