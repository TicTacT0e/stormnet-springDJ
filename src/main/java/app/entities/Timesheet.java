package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Timesheets")
public class Timesheet {
    @Id
    private int id;
    private int assignmentId;
    private int periodId;
    private String timesheetJson;
    private String status;

    public Timesheet() {
    }

    public Timesheet(int id, int periodId, int assignmentId,
                     String timesheetJson, String status) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.periodId = periodId;
        this.timesheetJson = timesheetJson;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Timesheet)) {
            return false;
        }
        Timesheet that = (Timesheet) object;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
