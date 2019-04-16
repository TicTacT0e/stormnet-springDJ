package app.entities;

import java.util.Date;
import java.util.Objects;

public class Logs {

    private Employee employee;
    private Project project;
    private long time;
    private String comment;
    private Date date;

    public Logs() {
    }

    public Logs(Employee employee, Project project, long time,
                String comment, Date date) {
        this.employee = employee;
        this.project = project;
        this.time = time;
        this.comment = comment;
        this.date = date;
    }

    public Logs(Logs logs) {
        this(logs.getEmployee(),
                logs.getProject(),
                logs.getTime(),
                logs.getComment(),
                logs.getDate());
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public long getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs logs = (Logs) object;
        return Double.compare(logs.time, time) == 0
                && Objects.equals(employee, logs.employee)
                && Objects.equals(project, logs.project)
                && Objects.equals(comment, logs.comment)
                && Objects.equals(date, logs.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, project, time, comment, date);
    }

    @Override
    public String toString() {
        return "Log{"
                + " Employee: " + employee.getName() + ';'
                + " Project: " + project.getName() + ';'
                + " Time: " + time + ';'
                + " Date: " + date + ';'
                + '\n'
                + "comment: '" + comment + '\''
                + '}';
    }
}
