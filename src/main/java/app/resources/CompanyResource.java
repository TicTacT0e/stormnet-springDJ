package app.resources;

import app.dao.CompanyDao;
import app.entities.Company;
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
    private CompanyDao companyDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getAll() {
        return companyDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company get(
            @PathParam("id") Integer id
    ) {
        return companyDao.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Company company) {
        companyDao.save(company);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(
            @PathParam("id") Integer id,
            Company company
    ) {
        if (id!= company.getId()) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        companyDao.edit(company);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(
            @PathParam("id") Integer id
    ) {
        companyDao.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    /*private static final Logger log = Logger.getLogger(CompanyResource.class);

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ProjectDao projectDao;

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
    }*/
}
