package app.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Assignments")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
//@JsonIgnoreProperties("assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private int projectId;
    //    private int employeeId;
    private int activityId;

//    @OneToOne
//    @JoinColumn(name = "projectId")
//    private Project project;
//    @MapsId("id")

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId",
            updatable = false, insertable = false)
//    @JsonIgnoreProperties("assignments")
    private Employee employee;
    //    @OneToOne
//    @JoinColumn(name = "activityId")
//    private Activity activity;
    private int workLoad;


    public Assignment() {
    }


    public Employee getEmployee() {
        return employee;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    //    public Assignment(int id, int projectId, int employeeId,
//                      int activityId, int workLoad) {
//        this.id = id;
//        this.projectId = projectId;
//        this.employeeId = employeeId;
//        this.activityId = activityId;
//        this.workLoad = workLoad;
//    }
//
//    public Assignment(Assignment assigment) {
//        this(
//                assigment.getId(),
//                assigment.getProjectId(),
//                assigment.getEmployeeId(),
//                assigment.getActivityId(),
//                assigment.getWorkLoad()
//        );
//    }
//
//    public int getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(int projectId) {
//        this.projectId = projectId;
//    }
//
//    public int getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(int employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    public int getWorkLoad() {
//        return workLoad;
//    }
//
//    public void setWorkLoad(int workLoadInMinutes) {
//        this.workLoad = workLoadInMinutes;
//    }
//
//    public int getActivityId() {
//        return activityId;
//    }
//
//    public void setActivityId(int activityId) {
//        this.activityId = activityId;
//    }

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
