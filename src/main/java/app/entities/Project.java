package app.entities;

import app.utils.TimeUtil;

import java.util.Date;
import java.util.Objects;

public class Project {

    private int id;
    private String name;
    private String logoUrl;
    private Date startDate;
    private Date endDate;
    private long manHours;
    private String code;
    private String colour;
    private String description;


    public Project() {
    }

    public Project(int id, String name, String logoUrl,
                   Date startDate, Date endDate, long manHoursInMilliseconds, String code, String colour, String description) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manHours = manHoursInMilliseconds;
        this.code = code;
        this.colour = colour;
        this.description = description;
    }

    public Project(int id, String name, String logoUrl,
                   Date startDate, Date endDate, int manHoursInHours, String code, String colour, String description) {
        this(id, name, logoUrl, startDate, endDate,
                TimeUtil.hoursToMillisecond(manHoursInHours), code, colour, description);
    }

    public Project(Project project) {
        this(project.getId(),
                project.getName(),
                project.getLogoUrl(),
                project.getStartDate(),
                project.getEndDate(),
                project.getManHours(),
                project.getCode(),
                project.getColour(),
                project.getDescription());
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
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

    public void setManHours(long manHoursInMilliseconds) {
        this.manHours = manHoursInMilliseconds;
    }

    public void setManHours(int manHoursInHours) {
        this.manHours = TimeUtil.hoursToMillisecond(manHoursInHours);
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
        return id == project.id
                && Double.compare(project.manHours, manHours) == 0
                && Objects.equals(name, project.name)
                && Objects.equals(logoUrl, project.logoUrl)
                && Objects.equals(startDate, project.startDate)
                && Objects.equals(endDate, project.endDate)
                && Objects.equals(code, project.code)
                && Objects.equals(colour, project.colour)
                && Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, logoUrl, startDate, endDate, manHours, code, colour, description);
    }

    @Override
    public String toString() {
        return "Project{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", logoUrl='" + logoUrl + '\''
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", manHours=" + manHours + '\''
                + ", code=" + code
                + ", colour" + colour + '\''
                + ", description=" + description
                + '}';
    }
}
