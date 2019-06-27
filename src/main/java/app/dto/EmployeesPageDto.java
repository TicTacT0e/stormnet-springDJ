package app.dto;

import java.util.List;

public class EmployeesPageDto {

    private List<EmployeesPageItemDto> employeeItems;

    public EmployeesPageDto() {
    }

    public List<EmployeesPageItemDto> getEmployeeItems() {
        return employeeItems;
    }

    public void setEmployeeItems(List<EmployeesPageItemDto> employeeItems) {
        this.employeeItems = employeeItems;
    }
}
