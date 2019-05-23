package app.entities;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.util.Date;
import java.util.List;

public class ProjectPage {
    private String projectColor;
    private String projectName;
    private String projectCode;
    @ElementCollection
    @CollectionTable(name = "Employee", joinColumns = @JoinColumn(name = "Project"))
    private List<Employee> team;
    private Date projectStartDate;
    private long projectLoading;

    public ProjectPage() {
    }

    public String getProjectColor() {
        return projectColor;
    }

    public void setProjectColor(String projectColor) {
        this.projectColor = projectColor;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public long getProjectLoading() {
        return projectLoading;
    }

    public void setProjectLoading(long projectLoading) {
        this.projectLoading = projectLoading;
    }
}

