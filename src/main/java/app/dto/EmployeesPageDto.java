package app.dto;

import java.util.List;

public class EmployeesPageDto {

    private List<EmployeesPageItemDto> items;

    public EmployeesPageDto() {
    }

    public List<EmployeesPageItemDto> getItems() {
        return items;
    }

    public void setItems(List<EmployeesPageItemDto> items) {
        this.items = items;
    }
}
