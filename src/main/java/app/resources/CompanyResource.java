package app.resources;


import app.dao.CompanyDao;
import app.dao.EmployeeDao;
import app.dao.ProjectDao;
import app.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/company")
public class CompanyResource {

    private static final Logger log = Logger.getLogger(CompanyResource.class);

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ProjectDao projectDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getAll() {
        log.warn("getAll method called");      
        return companyDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company get(@PathParam("id") int id) {
        return companyDao.findById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Company company) {
        companyDao.save(company);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Company company) {
        companyDao.edit(company);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        companyDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/add/employee/{companyId}/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployeeToCompany(
            @PathParam("companyId") int companyId,
            @PathParam("employeeId") int employeeId) {
        companyDao.addEmployeeToCompany(companyDao.findById(companyId),
                employeeDao.findById(employeeId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Path("/add/project/{companyId}/{projectId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProjectToCompany(
            @PathParam("companyId") int companyId,
            @PathParam("projectId") int projectId) {
        companyDao.addProjectToCompany(companyDao.findById(companyId),
                projectDao.findById(projectId));
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
