package app.entities;

import java.util.Date;
import java.util.Objects;

public class Logs {

    private int id;
    private int employee;
    private int project;
    private int time;
    private String comment;
    private Date date;

    public Logs (){

    }
    public Logs(int project, int employee, int time,
                String comment, Date date) {
        this.project = project;
        this.employee = employee;
        this.time = time;
        this.comment = comment;
        this.date = date;
    }
    public Logs(int id, int project, int employee, int time,
                String comment, Date date) {
        this.id = id;
        this.project = project;
        this.employee = employee;
        this.time = time;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
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
                + " Project: " + project + ';'
                + " Employee: " + employee + ';'
                + " Time: " + time + ';'
                + " Date: " + date + ';'
                + '\n'
                + "comment: '" + comment + '\''
                + '}';
    }
}
