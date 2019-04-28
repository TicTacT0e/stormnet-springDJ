package app.resources;

import app.dao.BasicCrudDao;
import app.dto.CompanySettingsDto;
import app.entities.Activity;
import app.entities.Project;
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

@Component
public class SettingsResource {

    @Autowired
    BasicCrudDao<Settings> settingsDao;
    @Autowired
    BasicCrudDao<Activity> activityDao;
    @Autowired
    BasicCrudDao<Project> projectDao;

    @POST
    @Path("/company/{companyId}/settings")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(
            @PathParam("companyId") int companyId,
            CompanySettingsDto companySettingsDto
    ) {
        List<Activity> activities = companySettingsDto.getCompanyActivities();
        for(Activity activity : activities) {
            activityDao.create(activity);
        }
        List<Project> projects = companySettingsDto.getDefaultProject();
        for(Project project : projects) {
            projectDao.create(project);
        }
        settingsDao.create(new Settings());
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
