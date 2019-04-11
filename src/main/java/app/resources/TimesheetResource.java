package app.resources;

import app.dao.TimesheetDao;
import app.entities.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/Timesheet")
public class TimesheetResource {

    @Autowired
    private TimesheetDao timesheetDao;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Timesheet timesheet) {
        timesheetDao.add(timesheet);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Timesheet> getAll() {
        return timesheetDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Timesheet get(@PathParam("id") int id) {
        return timesheetDao.findById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        timesheetDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Timesheet timesheet) {
        timesheetDao.update(timesheet);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

}
