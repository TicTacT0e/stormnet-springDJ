package app.entities;

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
}
