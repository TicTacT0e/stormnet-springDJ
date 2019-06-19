package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    private int id;
    private int companyId;
    private String name;
    private String logoUrl;
    private Date startDate;
    private Date endDate;
    private long manHours;
    private String code;
    private String color;
    private String description;

    public Project() {
    }

    public Project(int id, int companyId, String name, String logoUrl,
                   Date startDate, Date endDate, long manHours,
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

    public long getManHours() {
        return manHours;
    }

    public void setManHours(long manHours) {
        this.manHours = manHours;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Project project = (Project) object;
        if (id != project.id) {
            return false;
        }
        if (companyId != project.companyId) {
            return false;
        }
        if (!Objects.equals(name, project.name)) {
            return false;
        }
        if (!Objects.equals(logoUrl, project.logoUrl)) {
            return false;
        }
        return !Objects.equals(code, project.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyId, name, logoUrl, code);
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
