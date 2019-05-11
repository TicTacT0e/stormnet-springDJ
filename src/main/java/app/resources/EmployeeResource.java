package app.resources;

import app.dao.EmployeeDao;
import app.entities.Employee;
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
    private EmployeeDao employeeDao;
    //@Autowired
    //private ProjectDao projectDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll() {
        return employeeDao.findAll();
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
        employeeDao.create(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("id") int id,
            Employee employee
    ) {
        if (id!= employee.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        employeeDao.update(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(
            @PathParam("id") int id
    ) {
        employeeDao.deleteById(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
