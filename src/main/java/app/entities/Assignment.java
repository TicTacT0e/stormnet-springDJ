package app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Assignment")
public class Assignment {

    private int id;
    private int projectId;
    private int employeeId;
    private int activityId;
    private int workLoadInMinutes;

    public Assignment() {
    }

    public Assignment(int id, int projectId, int employeeId, int activityId, int workLoadInMinutes) {
        this.id = id;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.activityId = activityId;
        this.workLoadInMinutes = workLoadInMinutes;
    }

    public Assignment(Assignment assigment) {
        this(
                assigment.getId(),
                assigment.getProjectId(),
                assigment.getEmployeeId(),
                assigment.getActivityId(),
                assigment.getWorkLoadInMinutes()
        );
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getWorkLoadInMinutes() {
        return workLoadInMinutes;
    }

    public void setWorkLoadInMinutes(int workLoadInMinutes) {
        this.workLoadInMinutes = workLoadInMinutes;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment that = (Assignment) object;
        return getId() == that.getId()
                && getProjectId() == that.getProjectId()
                && getEmployeeId() == that.getEmployeeId()
                && getActivityId() == that.getActivityId()
                && getWorkLoadInMinutes() == that.getWorkLoadInMinutes();
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getId(), getProjectId(), getEmployeeId(),
                        getActivityId(), getWorkLoadInMinutes());
    }
}