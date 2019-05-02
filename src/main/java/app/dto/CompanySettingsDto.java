package app.dto;

import app.entities.Activity;
import app.entities.Integration;
import app.entities.Project;

import java.util.List;
import java.util.Map;

public class CompanySettingsDto {

    private List<Activity> companyActivities;
    private List<Project> defaultProject;
    private Map<String, String> settings;
    private List<Integration> integrations;

    public CompanySettingsDto() {
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

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public List<Integration> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(List<Integration> integrations) {
        this.integrations = integrations;
    }
}
