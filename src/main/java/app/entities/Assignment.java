package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Assignments")
public class Assignment {

    @Id
    private int id;
    private int projectId;
    private int userId;
    private int activityId;
    private int workLoad;

    public Assignment() {
    }

    public Assignment(int id, int projectId, int employeeId,
                      int activityId, int workLoad) {
        this.id = id;
        this.projectId = projectId;
        this.userId = employeeId;
        this.activityId = activityId;
        this.workLoad = workLoad;
    }

    public Assignment(Assignment assigment) {
        this(
                assigment.getId(),
                assigment.getProjectId(),
                assigment.getUserId(),
                assigment.getActivityId(),
                assigment.getWorkLoad()
        );
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
