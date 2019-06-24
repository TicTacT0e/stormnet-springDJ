package app.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Invitations")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int employeeId;
    private String code;
    private Date dateEnd;
    private String status;

    @OneToOne(targetEntity = Employee.class,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId", updatable = false, insertable = false)
    @JsonbTransient
    private Employee employee;

    public Invitation() {
    }

    public Invitation(int id, int employeeId, String code,
                      Date dateEnd, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.code = code;
        this.dateEnd = dateEnd;
        this.status = status;
    }

    public Invitation(Invitation invitation) {
        this(
                invitation.getId(),
                invitation.getEmployeeId(),
                invitation.getCode(),
                invitation.getDateEnd(),
                invitation.getStatus()
        );
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getCode() {
        return code;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Invitation{"
                + "id=" + id
                + ", employeeId=" + employeeId
                + ", code='" + code + '\''
                + ", dateEnd=" + dateEnd
                + ", status='" + status + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Invitation that = (Invitation) o;

        if (id != that.id) {
            return false;
        }
        if (employeeId != that.employeeId) {
            return false;
        }
        if (!Objects.equals(code, that.code)) {
            return false;
        }
        if (!Objects.equals(dateEnd, that.dateEnd)) {
            return false;
        }
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
