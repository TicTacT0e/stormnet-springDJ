package app.entities;

import java.util.Objects;

public class Assignment {

    private int projectId;
    private int employeeId;
    private int workLoadInMinutes;

    public Assignment() {
    }

    public Assignment(int projectId, int employeeId, int workLoadInMinutes) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.workLoadInMinutes = workLoadInMinutes;
    }

    public Assignment(Assignment assigment) {
        this(
                assigment.getProjectId(),
                assigment.getEmployeeId(),
                assigment.workLoadInMinutes
        );
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getWorkLoadInMinutes() {
        return workLoadInMinutes;
    }

    public void setWorkLoadInMinutes(int workLoadInMinutes) {
        this.workLoadInMinutes = workLoadInMinutes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment assigment = (Assignment) object;
        return getProjectId() == assigment.getProjectId()
                && getEmployeeId() == assigment.getEmployeeId()
                && getWorkLoadInMinutes() == assigment.getWorkLoadInMinutes();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getProjectId(),
                getEmployeeId(),
                getWorkLoadInMinutes()
        );
    }

    @Override
    public String toString() {
        return "Assignment{"
                + "projectId=" + projectId
                + ", employeeId=" + employeeId
                + ", workLoadInMinutes=" + workLoadInMinutes
                + '}';
    }
}
