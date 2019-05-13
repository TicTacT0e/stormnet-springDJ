package app.entities;

import java.util.List;

public class ProjectEditPage {

    private Project project;
    private List<Employee> team;
    private List integrations;
    private List<Timesheet> workload;
    private long manHoursExpected;
    private long currentlySpentManHours;

    public ProjectEditPage() {
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }

    public void setIntegrations(List integrations) {
        this.integrations = integrations;
    }

    public void setWorkload(List<Timesheet> workload) {
        this.workload = workload;
    }

    public void setManHoursExpected(long manHoursExpected) {
        this.manHoursExpected = manHoursExpected;
    }

    public void setCurrentlySpentManHours(long currentlySpentManHours) {
        this.currentlySpentManHours = currentlySpentManHours;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
