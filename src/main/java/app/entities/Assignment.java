package app.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private Integer employeeId;
    private Integer projectId;
    private Integer activityId;
    private Integer workLoad;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId",
            updatable = false, insertable = false)
    private Employee employee;
    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "projectId",
            updatable = false, insertable = false)
    private Project project;
    @ManyToOne(targetEntity = Activity.class)
    @JoinColumn(name = "activityId",
            updatable = false, insertable = false)
    private Activity activity;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY,
            targetEntity = Timesheet.class, cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Timesheet> timesheets;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY,
            targetEntity = Log.class, cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Log> logs;

    public Assignment() {
    }

    public Assignment(Integer id, Integer projectId, Integer employeeId,
                      Integer activityId, Integer workLoad) {
        this.id = id;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.activityId = activityId;
        this.workLoad = workLoad;
    }

    public Assignment(Assignment assigment) {
        this(
                assigment.getId(),
                assigment.getProjectId(),
                assigment.getEmployeeId(),
                assigment.getActivityId(),
                assigment.getWorkLoad()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<Timesheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(List<Timesheet> timesheet) {
        this.timesheets = timesheet;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> log) {
        this.logs = log;
    }

    public Integer getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(Integer workLoad) {
        this.workLoad = workLoad;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
