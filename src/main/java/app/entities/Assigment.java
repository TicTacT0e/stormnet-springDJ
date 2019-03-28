package app.entities;

public class Assigment {

    private int projectId;
    private int employeeId;
    private int workLoadInMinuts;

    public Assigment() {}

    public Assigment(int projectId, int employeeId, int workLoadInMinuts) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.workLoadInMinuts = workLoadInMinuts;
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

    public int getWorkLoadInMinuts() {
        return workLoadInMinuts;
    }

    public void setWorkLoadInMinuts(int workLoadInMinuts) {
        this.workLoadInMinuts = workLoadInMinuts;
    }
}
