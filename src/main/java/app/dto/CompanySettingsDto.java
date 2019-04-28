package app.dto;

import app.entities.Activity;
import app.entities.Integration;
import app.entities.Project;

import java.util.List;

public class CompanySettingsDto {

    private List<Activity> companyActivities;
    private List<Project> defaultProject;
    private String startWeek;
    private int workLoad;
    private int approvalPeriod;
    private boolean autoSubmitAtEndPeriod;
    private boolean timeDifferenceNotification;
    private int timeDifferenceParameter;
    private String timeDifferenceType;
    private boolean forgetTimesheets;
    private boolean commentRequired;
    private String commentValidationRule;
    private List<Integration> integrations;

    public CompanySettingsDto() {
    }

    public CompanySettingsDto(List<Activity> companyActivities, List<Project> defaultProject, String startWeek, int workLoad, int approvalPeriod, boolean autoSubmitAtEndPeriod, boolean timeDifferenceNotification, int timeDifferenceParameter, String timeDifferenceType, boolean forgetTimesheets, boolean commentRequired, String commentValidationRule, List<Integration> integrations) {
        this.companyActivities = companyActivities;
        this.defaultProject = defaultProject;
        this.startWeek = startWeek;
        this.workLoad = workLoad;
        this.approvalPeriod = approvalPeriod;
        this.autoSubmitAtEndPeriod = autoSubmitAtEndPeriod;
        this.timeDifferenceNotification = timeDifferenceNotification;
        this.timeDifferenceParameter = timeDifferenceParameter;
        this.timeDifferenceType = timeDifferenceType;
        this.forgetTimesheets = forgetTimesheets;
        this.commentRequired = commentRequired;
        this.commentValidationRule = commentValidationRule;
        this.integrations = integrations;
    }

    public CompanySettingsDto(CompanySettingsDto companySettingsDto) {
        this(
                companySettingsDto.getCompanyActivities(),
                companySettingsDto.getDefaultProject(),
                companySettingsDto.getStartWeek(),
                companySettingsDto.getWorkLoad(),
                companySettingsDto.getApprovalPeriod(),
                companySettingsDto.isAutoSubmitAtEndPeriod(),
                companySettingsDto.isTimeDifferenceNotification(),
                companySettingsDto.getTimeDifferenceParameter(),
                companySettingsDto.getTimeDifferenceType(),
                companySettingsDto.isForgetTimesheets(),
                companySettingsDto.isCommentRequired(),
                companySettingsDto.getCommentValidationRule(),
                companySettingsDto.getIntegrations()
        );
    }

    public List<Activity> getCompanyActivities() {
        return companyActivities;
    }

    public void setCompanyActivities(List<Activity> companyActivities) {
        this.companyActivities = companyActivities;
    }

    public List<Project> getDefaultProject() {
        return defaultProject;
    }

    public void setDefaultProject(List<Project> defaultProject) {
        this.defaultProject = defaultProject;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public int getApprovalPeriod() {
        return approvalPeriod;
    }

    public void setApprovalPeriod(int approvalPeriod) {
        this.approvalPeriod = approvalPeriod;
    }

    public boolean isAutoSubmitAtEndPeriod() {
        return autoSubmitAtEndPeriod;
    }

    public void setAutoSubmitAtEndPeriod(boolean autoSubmitAtEndPeriod) {
        this.autoSubmitAtEndPeriod = autoSubmitAtEndPeriod;
    }

    public boolean isTimeDifferenceNotification() {
        return timeDifferenceNotification;
    }

    public void setTimeDifferenceNotification(boolean timeDifferenceNotification) {
        this.timeDifferenceNotification = timeDifferenceNotification;
    }

    public int getTimeDifferenceParameter() {
        return timeDifferenceParameter;
    }

    public void setTimeDifferenceParameter(int timeDifferenceParameter) {
        this.timeDifferenceParameter = timeDifferenceParameter;
    }

    public String getTimeDifferenceType() {
        return timeDifferenceType;
    }

    public void setTimeDifferenceType(String timeDifferenceType) {
        this.timeDifferenceType = timeDifferenceType;
    }

    public boolean isForgetTimesheets() {
        return forgetTimesheets;
    }

    public void setForgetTimesheets(boolean forgetTimesheets) {
        this.forgetTimesheets = forgetTimesheets;
    }

    public boolean isCommentRequired() {
        return commentRequired;
    }

    public void setCommentRequired(boolean commentRequired) {
        this.commentRequired = commentRequired;
    }

    public String getCommentValidationRule() {
        return commentValidationRule;
    }

    public void setCommentValidationRule(String commentValidationRule) {
        this.commentValidationRule = commentValidationRule;
    }

    public List<Integration> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(List<Integration> integrations) {
        this.integrations = integrations;
    }
}
