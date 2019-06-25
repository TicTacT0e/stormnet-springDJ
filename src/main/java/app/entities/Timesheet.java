package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Timesheets")
public class Timesheet {
    @Id
    private Integer id;
    private Integer assignmentId;
    private Integer periodId;
    private String timesheetJson;
    private String status;
    private Date fromDate;
    private Date toDate;

    public Timesheet() {
    }

    public Timesheet(Integer id, Integer periodId, Integer assignmentId,
                     String timesheetJson, String status, Date fromDate, Date toDate) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.periodId = periodId;
        this.timesheetJson = timesheetJson;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public String getTimesheetJson() {
        return timesheetJson;
    }

    public void setTimesheetJson(String timesheetJson) {
        this.timesheetJson = timesheetJson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
