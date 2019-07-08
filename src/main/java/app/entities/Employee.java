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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private Integer companyId;
    private Integer userId;
    private String roleId;
    private Integer workLoad;
    private String status;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId",
            updatable = false, insertable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Assignment.class,
            mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Assignment> assignments;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Notification.class,
            mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Notification> notifications;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "companyId",
            updatable = false, insertable = false)
    private Company company;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "roleId",
            updatable = false, insertable = false)
    private Role role;

    @OneToOne(optional = false, targetEntity = Invitation.class,
            cascade = CascadeType.ALL, mappedBy = "employee")
    private Invitation invitation;

    public Employee() {
    }

    public Employee(Integer id, Integer companyId, Integer userId,
                    String roleId, Integer workLoad, String status) {
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
        this.roleId = roleId;
        this.workLoad = workLoad;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(Integer workLoad) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) object;
        return getId().equals(employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
