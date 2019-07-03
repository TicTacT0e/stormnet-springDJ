package app.dto;

import java.util.List;

public class EmployeeProfileDto {

    private String name;
    private String photoUrl;
    private String role;
    private Integer workload;
    private String email;
    private String phone;
    private List<EmployeeProjectItemDto> employeeProjects;
    private TimesheetDto timesheetCurrentWeek;
    private List<TimesheetDto> timesheetsPending;

    public EmployeeProfileDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<EmployeeProjectItemDto> getEmployeeProjects() {
        return employeeProjects;
    }

    public void setEmployeeProjects(
            List<EmployeeProjectItemDto> employeeProjects
    ) {
        this.employeeProjects = employeeProjects;
    }

    public TimesheetDto getTimesheetCurrentWeek() {
        return timesheetCurrentWeek;
    }

    public void setTimesheetCurrentWeek(TimesheetDto timesheetCurrentWeek) {
        this.timesheetCurrentWeek = timesheetCurrentWeek;
    }

    public List<TimesheetDto> getTimesheetsPending() {
        return timesheetsPending;
    }

    public void setTimesheetsPending(List<TimesheetDto> timesheetsPending) {
        this.timesheetsPending = timesheetsPending;
    }
}
