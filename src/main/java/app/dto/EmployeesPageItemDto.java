package app.dto;

import java.util.List;

public class EmployeesPageItemDto {

    private String name;
    private String photoUrl;
    private String role;
    private Double planned;
    private Double actual;
    private String status;
    private List<TimesheetItemsDto> pendingApprovalDtoList;

    public EmployeesPageItemDto() {
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

    public List<TimesheetItemsDto> getPendingApprovalDtoList() {
        return pendingApprovalDtoList;
    }

    public void setPendingApprovalDtoList(
            List<TimesheetItemsDto> pendingApprovalDtoList
    ) {
        this.pendingApprovalDtoList = pendingApprovalDtoList;
    }
}
