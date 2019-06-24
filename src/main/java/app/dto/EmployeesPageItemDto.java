package app.dto;

import java.util.List;

public class EmployeesPageItemDto {

    private String name;
    private String role;
    private Double planned;
    private Double actual;
    private String status;
    private List<TimesheetPendingApprovalDto> pendingApprovalDtoList;

    public EmployeesPageItemDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getPlanned() {
        return planned;
    }

    public void setPlanned(Double planned) {
        this.planned = planned;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TimesheetPendingApprovalDto> getPendingApprovalDtoList() {
        return pendingApprovalDtoList;
    }

    public void setPendingApprovalDtoList(List<TimesheetPendingApprovalDto> pendingApprovalDtoList) {
        this.pendingApprovalDtoList = pendingApprovalDtoList;
    }
}
