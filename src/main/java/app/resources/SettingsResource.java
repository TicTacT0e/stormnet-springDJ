package app.resources;

import app.dao.BasicCrudDao;
import app.dao.ProjectDao;
import app.dto.CompanySettingsDto;
import app.entities.Activity;
import app.entities.Integration;
import app.entities.Project;
import app.entities.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/company/{companyId}/settings")
public class SettingsResource {

    @Autowired
    BasicCrudDao<Settings> settingsDao;
    @Autowired
    BasicCrudDao<Activity> activityDao;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    BasicCrudDao<Integration> integrationDao;

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
        List<Project> projects = companySettingsDto.getDefaultProject();
        for (Project project : projects) {
            projectDao.save(project);
        }
        settingsDao.create(new Settings(
                companyId,
                "workLoad",
                String.valueOf(companySettingsDto.getWorkLoad())
        ));
        settingsDao.create(new Settings(
                companyId,
                "approvalPeriod",
                String.valueOf(companySettingsDto.getApprovalPeriod())
        ));
        settingsDao.create(new Settings(
                companyId,
                "autoSubmitAtEndPeriod",
                String.valueOf(companySettingsDto.isAutoSubmitAtEndPeriod())
        ));
        settingsDao.create(new Settings(
                companyId,
                "timeDifferenceNotification",
                String.valueOf(companySettingsDto.isTimeDifferenceNotification())
        ));
        settingsDao.create(new Settings(
                companyId,
                "timeDifferenceParameter",
                String.valueOf(companySettingsDto.getTimeDifferenceParameter())
        ));
        settingsDao.create(new Settings(
                companyId,
                "timeDifferenceType",
                companySettingsDto.getTimeDifferenceType()
        ));
        settingsDao.create(new Settings(
                companyId,
                "forgetTimesheets",
                String.valueOf(companySettingsDto.isForgetTimesheets())
        ));
        settingsDao.create(new Settings(
                companyId,
                "commentRequired",
                String.valueOf(companySettingsDto.isCommentRequired())
        ));
        settingsDao.create(new Settings(
                companyId,
                "commentValidationRule",
                companySettingsDto.getCommentValidationRule()
        ));
        List<Integration> integrations = companySettingsDto.getIntegrations();
        for (Integration integration : integrations) {
            integrationDao.create(integration);
        }
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
