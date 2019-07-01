package app.dto;

import java.util.List;

public class TimesheetDto {

    private List<LogDto> logs;
    private Double planned;
    private Double actual;
    private List<TimesheetProjectItem> projects;

    public TimesheetDto() {
    }

    public List<LogDto> getLogs() {
        return logs;
    }

    public void setLogs(List<LogDto> logs) {
        this.logs = logs;
    }

    public Double getPlanned() {
        return planned;
    }

    public void setPlanned(Double planned) {
        this.planned = planned;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public List<TimesheetProjectItem> getProjects() {
        return projects;
    }

    public void setProjects(List<TimesheetProjectItem> projects) {
        this.projects = projects;
    }
}
