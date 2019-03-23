package app.entities;

import java.util.Objects;

public class Assigment {

    private int projectId;
    private int employeeId;
    private int workLoadInMinutes;

    public Assigment() {}

    public Assigment(int projectId, int employeeId, int workLoadInMinutes) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.workLoadInMinutes = workLoadInMinutes;
    }

    public Assigment(Assigment assigment) {
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
        if (this == object) { return true; }
        if (!(object instanceof Assigment)) { return false; }
        Assigment assigment = (Assigment) object;
        return getProjectId() == assigment.getProjectId() &&
                getEmployeeId() == assigment.getEmployeeId() &&
                getWorkLoadInMinutes() == assigment.getWorkLoadInMinutes();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(), getEmployeeId(), getWorkLoadInMinutes());
    }

    @Override
    public String toString() {
        return "Assigment{" +
                "projectId=" + projectId +
                ", employeeId=" + employeeId +
                ", workLoadInMinutes=" + workLoadInMinutes +
                '}';
    }
}
