package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "assignment_1")
public class Assignment {
    @Id
    private int id;
    private int employeeId;
    private int projectId;
    private int activityId;
    private int workLoad;

    public Assignment() {
    }

    public Assignment(int id, int employeeId, int projectId, int activityId, int workLoad) {
        this.id = id;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.activityId = activityId;
        this.workLoad = workLoad;
    }

    public Assignment(Assignment assigment) {
        this(
                assigment.getId(),
                assigment.getEmployeeId(),
                assigment.getProjectId(),
                assigment.getActivityId(),
                assigment.getWorkLoad());
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

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoadInMinutes) {
        this.workLoad = workLoadInMinutes;
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
                && getEmployeeId() == that.getEmployeeId()
                && getProjectId() == that.getProjectId()
                && getActivityId() == that.getActivityId()
                && getWorkLoad() == that.getWorkLoad();
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getId(), getEmployeeId(), getProjectId(),
                        getActivityId(), getWorkLoad());
    }
}