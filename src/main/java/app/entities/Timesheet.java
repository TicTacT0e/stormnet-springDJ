package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Timesheets")
public class Timesheet {
    @Id
    private Integer id;
    private Integer assignmentId;
    private String timesheetJson;
    private String status;
    private Date fromDate;
    private Date toDate;

    @ManyToOne(targetEntity = Assignment.class)
    @JoinColumn(name = "assignmentId",
            updatable = false, insertable = false)
    private Assignment assignment;

    public Timesheet() {
    }

    public Timesheet(Integer id, Integer assignmentId, String timesheetJson,
                     String status, Date fromDate, Date toDate) {
        this.id = id;
        this.assignmentId = assignmentId;
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

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Timesheet)) {
            return false;
        }
        Timesheet timesheet = (Timesheet) object;
        return Objects.equals(getId(), timesheet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
