package app.resources;

import app.entities.Employee;
import app.service.EmployeeService;
import app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll() {
        return employeeService.getEmployees();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee get(@PathParam("id") int id) {
        return employeeService.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Employee employee) {
        employeeService.save(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Employee employee) {
        employeeService.edit(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        employeeService.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/assign/{employeeId}/{projectId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignToProject(@PathParam("employeeId") int employeeId,
                                    @PathParam("projectId") int projectId) {
        employeeService.assignToProject(employeeService.findById(employeeId),
                projectService.findById(projectId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
