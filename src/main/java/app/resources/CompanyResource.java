package app.resources;


import app.entities.Company;
import app.service.CompanyService;
import app.service.EmployeeService;
import app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/company")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getAll() {
        return companyService.getCompanies();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company get(@PathParam("id") int id) {
        return companyService.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Company company) {
        companyService.save(company);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Company company) {
        companyService.edit(company);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        companyService.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/add/employee/{companyId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployeeToCompany(@PathParam("companyId") int companyId,
                                         @PathParam("employeeId") int employeeId) {
        companyService.addEmployee(companyService.findById(companyId),
                employeeService.findById(employeeId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/add/project/{companyId}/{projectId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProjectToCompany(@PathParam("companyId") int companyId,
                                        @PathParam("projectId") int projectId) {
        companyService.addProject(companyService.findById(companyId),
                projectService.findById(projectId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
