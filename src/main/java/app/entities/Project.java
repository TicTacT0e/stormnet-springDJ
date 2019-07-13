package app.entities;

import java.util.Date;

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
@Table(name = "Projects")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private Integer companyId;
    private String name;
    private String logoUrl;
    private Date startDate;
    private Date endDate;
    private Long manHours;
    private String code;
    private String color;
    private String description;


    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "companyId",
            updatable = false, insertable = false)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Assignment.class,
            mappedBy = "project", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Assignment> assignments;

    public Project() {
    }

    public Project(Integer id, Integer companyId, String name, String logoUrl,
                   Date startDate, Date endDate, Long manHours,
                   String code, String color, String description) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.logoUrl = logoUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manHours = manHours;
        this.code = code;
        this.color = color;
        this.description = description;
    }

    public Project(Project project) {
        this(project.getId(),
                project.getCompanyId(),
                project.getName(),
                project.getLogoUrl(),
                project.getStartDate(),
                project.getEndDate(),
                project.getManHours(),
                project.getCode(),
                project.getColor(),
                project.getDescription());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getManHours() {
        return manHours;
    }

    public void setManHours(Long manHours) {
        this.manHours = manHours;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Project)) {
            return false;
        }
        Project project = (Project) object;
        return Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Project{"
                + "id=" + id
                + ", companyId='" + companyId + '\''
                + ", name='" + name + '\''
                + ", logoUrl='" + logoUrl + '\''
                + ", code=" + code
                + '}';
    }
}
