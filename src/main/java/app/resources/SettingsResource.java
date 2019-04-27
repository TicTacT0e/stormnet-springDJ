package app.resources;

import app.dao.BasicCrudDao;
import app.dto.CompanySettingsDto;
import app.entities.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class SettingsResource {

    @Autowired
    BasicCrudDao<Settings> settingsDao;

    @POST
    @Path("/company/{companyId}/settings")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(
            @PathParam("companyId") int companyId,
            CompanySettingsDto companySettingsDto
    ) {

        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
