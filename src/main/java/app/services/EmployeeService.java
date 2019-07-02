package app.services;

import app.dao.BasicCrudDao;
import app.dto.EmployeeProfileDto;
import app.dto.EmployeesPageDto;
import app.dto.EmployeesPageItemDto;
import app.dto.LogDto;
import app.dto.TimesheetDto;
import app.dto.TimesheetItemsDto;
import app.entities.Assignment;
import app.entities.Employee;
import app.entities.Log;
import app.entities.Timesheet;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private BasicCrudDao<Employee> employeeDao;

    private static final Date START_WEEK_DATE = DateTime.now()
            .withDayOfWeek(DateTimeConstants.MONDAY)
            .withTimeAtStartOfDay().toDate();
    private static final Date END_WEEK_DATE = DateTime.now()
            .withDayOfWeek(DateTimeConstants.SUNDAY)
            .withTimeAtStartOfDay().toDate();

    public EmployeesPageDto getAll() {
        return getEmployeesPageDto();
    }

    public EmployeeProfileDto get(int id) {
        return getEmployeeProfile(id);
    }

    public void add(Employee employee) {
        employeeDao.create(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(int id) {
        employeeDao.deleteById(id);
    }

    public void delete(Employee employee){
        employeeDao.delete(employee);
    }

    private EmployeeProfileDto getEmployeeProfile(int id) {
        Employee employee = employeeDao.findById(id);
        EmployeeProfileDto employeeProfileDto = new EmployeeProfileDto();
        employeeProfileDto.setName(employee.getUser().getName());
        employeeProfileDto.setPhotoUrl(employee.getUser().getPhotoUrl());
        employeeProfileDto.setRole(employee.getRole().getName());
        employeeProfileDto.setWorkload(employee.getWorkLoad());
        employeeProfileDto.setEmail(employee.getUser().getEmail());
        employeeProfileDto.setPhone(employee.getUser().getPhone());
        employeeProfileDto
                .setTimesheetCurrentWeek(getTimesheetCurrentWeek(employee));

        return employeeProfileDto;
    }

    private TimesheetDto getTimesheetCurrentWeek(Employee employee) {
        TimesheetDto currentWeekTimesheet = new TimesheetDto();
        List<Assignment> assignments = employee.getAssignments();
        List<LogDto> currentWeekLogs = getCurrentWeekLogs(assignments);
        currentWeekTimesheet.setLogs(currentWeekLogs);
        currentWeekTimesheet.setPlanned(Double.valueOf(employee.getWorkLoad()));
        currentWeekTimesheet.setActual(getActualWorkloadCurrentWeek(employee));
        return currentWeekTimesheet;
    }

    private List<LogDto> getCurrentWeekLogs(List<Assignment> assignments) {
        List<Log> logs = assignments.stream()
                .flatMap(assignment -> assignment.getLogs().stream()
                        .filter(log ->
                                log.getDate().after(START_WEEK_DATE)
                                        && log.getDate().before(END_WEEK_DATE)))
                .collect(Collectors.toList());

        return logs.stream()
                .map(log -> {
                    LogDto logDto = new LogDto();
                    logDto.setColor(log.getAssignment().getProject().getColor());
                    logDto.setComment(log.getComment());
                    logDto.setTime(log.getTime());
                    return logDto;
                }).collect(Collectors.toList());
    }

    private EmployeesPageDto getEmployeesPageDto() {
        EmployeesPageDto employeesPageDto = new EmployeesPageDto();
        List<Employee> employees = employeeDao.findAll();

        List<EmployeesPageItemDto> items
                = employees.stream()
                .map(employee -> {
                    EmployeesPageItemDto employeesPageItem
                            = new EmployeesPageItemDto();
                    employeesPageItem.setName(employee.getUser().getName());
                    employeesPageItem.setPhotoUrl(employee
                            .getUser().getPhotoUrl());
                    employeesPageItem.setRole(employee.getRole().getName());
                    employeesPageItem.setPlanned(Double
                            .valueOf(employee.getWorkLoad()));
                    employeesPageItem
                            .setActual(getActualWorkloadCurrentWeek(employee));
                    employeesPageItem.setStatus(employee.getStatus());
                    employeesPageItem.setPendingApprovalDtoList(
                            getEmployeeTimesheets(employee)
                    );
                    return employeesPageItem;
                }).collect(Collectors.toList());

        employeesPageDto.setEmployeeItems(items);
        return employeesPageDto;
    }

    private Double getActualWorkloadCurrentWeek(Employee employee) {
        List<Assignment> assignments = employee.getAssignments();
        List<Double> actualWorkLoadByAssignments
                = assignments.stream()
                .map(assignment ->
                        assignment.getLogs().stream()
                                .filter(log ->
                                        log.getDate().after(START_WEEK_DATE)
                                                && log.getDate().before(END_WEEK_DATE))
                                .mapToDouble(Log::getTime).sum()
                ).collect(Collectors.toList());
        return actualWorkLoadByAssignments.stream()
                .mapToDouble(Double::doubleValue).sum();
    }

    private List<TimesheetItemsDto> getEmployeeTimesheets(Employee employee) {
        List<Assignment> assignments = employee.getAssignments();

        List<Timesheet> timesheetsByEmployee =
                assignments.stream()
                        .flatMap(assignment -> assignment.getTimesheets().stream())
                        .collect(Collectors.toList());

        return timesheetsByEmployee.stream()
                .map(timesheet -> {
                    TimesheetItemsDto timesheetItem
                            = new TimesheetItemsDto();
                    timesheetItem.setFromDate(timesheet.getFromDate());
                    timesheetItem.setToDate(timesheet.getToDate());
                    timesheetItem.setPlanned(Double
                            .valueOf(timesheet.getAssignment().getWorkLoad()));
                    timesheetItem.setActual(
                            timesheet.getAssignment()
                                    .getLogs().stream()
                                    .mapToDouble(Log::getTime).sum()
                    );
                    return timesheetItem;
                }).collect(Collectors.toList());
    }
}
