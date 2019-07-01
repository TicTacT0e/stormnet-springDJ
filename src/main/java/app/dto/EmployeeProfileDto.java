package app.dto;

import java.util.List;

public class EmployeeProfileDto {

    private String name;
    private String photoUrl;
    private String role;
    private Integer workload;
    private String email;
    private String phone;
    private List<EmployeeProjectItem> employeeProjects;
    private TimesheetDto timesheetCurrentWeek;
    private List<TimesheetDto> timesheetsPending;

    public EmployeeProfileDto() {
    }
}
