package app.resources;

import app.dao.impl.TimesheetDao;
import app.entities.Timesheet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Timesheet")
public class TimesheetResource {

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Timesheet timesheet) {
        TimesheetDao.add(timesheet);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return TimesheetDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") int id) {
        return TimesheetDao.findById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        TimesheetDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Timesheet timesheet) {
        TimesheetDao.update(timesheet);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

}
