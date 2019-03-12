package app.service;

import app.dao.EmployeeDao;
import app.dao.ProjectDao;
import app.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/employee")
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ProjectDao projectDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee get(@PathParam("id") int id) {
        return employeeDao.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Employee employee) {
        employeeDao.save(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Employee employee) {
        employeeDao.edit(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        employeeDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/assign/{employeeId}/{projectId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignToProject(@PathParam("employeeId") int employeeId,
                                    @PathParam("projectId") int projectId) {
        employeeDao.assignToProject(employeeDao.findById(employeeId),
                projectDao.findById(projectId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
