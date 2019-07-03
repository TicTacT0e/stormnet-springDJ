package app.dto;

import java.util.Date;
import java.util.List;

public class TimesheetDto {

    private Date fromDate;
    private Date toDate;
    private List<LogDto> logs;
    private Double planned;
    private Double actual;
    private List<TimesheetProjectItem> projects;

    public TimesheetDto() {
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
