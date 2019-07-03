package app.resources;

import app.dto.EmployeeProfileDto;
import app.dto.EmployeesPageDto;
import app.entities.Employee;
import app.entities.Log;
import app.services.EmployeeService;
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
@Path("company/{companyId}/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GET
    @Path("/{id}/test")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> testEndPoint(@PathParam("id") int id) {
        return employeeService.testMethod(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeesPageDto getAll() {
        return employeeService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeProfileDto get(@PathParam("id") int id) {
        return employeeService.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Employee employee) {
        employeeService.add(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Employee employee) {
        employeeService.edit(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        employeeService.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
