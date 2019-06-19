package app.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String name;
    private String logo;
    private int ownerId;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Employee.class,
            mappedBy = "company", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Employee> employees;

    public Company() {
    }

    public Company(int id, String name, String logoUrl, int ownerId) {
        this.id = id;
        this.name = name;
        this.logo = logoUrl;
        this.ownerId = ownerId;
    }

    public Company(Company company) {
        this(company.getId(),
                company.getName(),
                company.getLogo(),
                company.getOwnerId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return Objects.equals(getId(), company.getId())
                && Objects.equals(getName(), company.getName())
                && Objects.equals(getLogo(), company.getLogo())
                && Objects.equals(getOwnerId(), company.getOwnerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogo(), getOwnerId());
    }

    @Override
    public String toString() {
        return "Company{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", logo='" + logo + '\''
                + ", ownerId=" + ownerId
                + '}';
    }

}
