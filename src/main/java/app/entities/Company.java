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
    private Integer id;
    private String name;
    private String logo;
    private Integer ownerId;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Employee.class,
            mappedBy = "company", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Employee> employees;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Settings.class,
            mappedBy = "company")
    @JsonbTransient
    private List<Settings> settings;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Integration.class,
            mappedBy = "company", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Integration> integrations;
  
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Project.class,
            mappedBy = "company")
    @JsonbTransient
    private List<Project> projects;

    public Company() {
    }

    public Company(Integer id, String name, String logoUrl, Integer ownerId) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Settings> getSettings() {
        return settings;
    }

    public void setSettings(List<Settings> settings) {
        this.settings = settings;
    }
  
    public List<Integration> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(List<Integration> integrations) {
        this.integrations = integrations;
    }
  
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Company)) {
            return false;
        }
        Company company = (Company) object;
        return Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
