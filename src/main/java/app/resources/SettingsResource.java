package app.resources;

import app.dao.BasicCrudDao;
import app.dto.CompanySettingsDto;
import app.entities.Activity;
import app.entities.Integration;
import app.entities.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component
@Path("/company/{companyId}/settings")
public class SettingsResource {

    @Autowired
    private BasicCrudDao<Settings> settingsDao;
    @Autowired
    private BasicCrudDao<Activity> activityDao;
    @Autowired
    private BasicCrudDao<Integration> integrationDao;

    private static final String DEFAULT_PROJECT_SETTINGS_KEY
            = "default project";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(
            @PathParam("companyId") int companyId,
            final CompanySettingsDto companySettingsDto
    ) {
        List<Activity> activities = companySettingsDto.getCompanyActivities();
        for (Activity activity : activities) {
            activityDao.create(activity);
        }
        List<Integer> defaultProjectIDs
                = companySettingsDto.getDefaultProjectIDs();
        settingsDao.create(new Settings(
                companyId,
                DEFAULT_PROJECT_SETTINGS_KEY,
                defaultProjectIDs.toString()
        ));
        Map<String, String> settings = companySettingsDto.getSettings();
        settings.forEach((key, value) ->
                settingsDao.create(new Settings(
                        companyId,
                        key,
                        value
                )));
        List<Integration> integrations = companySettingsDto.getIntegrations();
        for (Integration integration : integrations) {
            integration.setCompanyId(companyId);
            integrationDao.create(integration);
        }
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
