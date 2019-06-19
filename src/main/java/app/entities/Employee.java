package app.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int companyId;
    private int userId;
    private String roleId;
    private int workLoad;
    private String status;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Assignment.class,
            mappedBy = "employee")
    @JsonbTransient
    private List<Assignment> assignments;

    public Employee() {
    }

    public Employee(int id, int companyId, int userId, String roleId,
                    int workLoad, String status) {
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
        this.roleId = roleId;
        this.workLoad = workLoad;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Assignment> getAssignments() {
        return assignments;
    }


    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("companyId=" + companyId)
                .add("userId=" + userId)
                .add("roleId='" + roleId + "'")
                .add("workLoad=" + workLoad)
                .add("status='" + status + "'")
                .toString();
    }
}
