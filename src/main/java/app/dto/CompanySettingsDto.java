package app.dto;

import app.entities.Activity;
import app.entities.Integration;
import app.entities.Project;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class CompanySettingsDto {

    private List<Activity> companyActivities = new LinkedList<>();
    private List<Project> defaultProject = new LinkedList<>();
    private Date startweek;
    private int workLoad;
    private Time approvalPeriod;
    private boolean autoSubmitAtEndPeriod;
    private boolean timeDifferenceNotification;
    private int timeDifferenceParameter;
    private String timeDefferenceType;
    private boolean forgetTimesheets;
    private boolean commentRequired;
    private String commentValidationRule;
    private List<Integration> integrations;

}
