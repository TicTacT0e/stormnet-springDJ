package app.services;

import app.dao.BasicCrudDao;
import app.dto.EmployeeProfileDto;
import app.dto.EmployeeProjectItemDto;
import app.dto.EmployeesPageDto;
import app.dto.EmployeesPageItemDto;
import app.dto.LogDto;
import app.dto.TimesheetDto;
import app.dto.TimesheetItemsDto;
import app.dto.TimesheetProjectItemDto;
import app.entities.Assignment;
import app.entities.Employee;
import app.entities.Log;
import app.services.util.WeekPeriodUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private BasicCrudDao<Employee> employeeDao;
    @Autowired
    private PdfService pdfService;

    private WeekPeriodUtil currentWeekPeriod
            = new WeekPeriodUtil(DateTime.now().toDate());

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

    public void delete(Employee employee) {
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
        employeeProfileDto
                .setEmployeeProjects(
                        getProjectItemsForEmployeeProfile(employee));
        employeeProfileDto
                .setTimesheetsPending(
                        getPendingForApprovalTimesheets(employee));
        return employeeProfileDto;
    }

    private List<EmployeeProjectItemDto> getProjectItemsForEmployeeProfile(
            Employee employee
    ) {
        return employee.getAssignments().stream()
                .map(assignment -> {
                    EmployeeProjectItemDto projectItem
                            = new EmployeeProjectItemDto();
                    projectItem.setName(assignment.getProject().getName());
                    projectItem.setColor(assignment.getProject().getColor());
                    projectItem.setActivity(assignment.getActivity().getName());
                    projectItem.setWorkload(assignment.getWorkLoad());
                    return projectItem;
                })
                .collect(Collectors.toList());
    }

    private List<TimesheetDto> getPendingForApprovalTimesheets(
            Employee employee) {
        Map<WeekPeriodUtil, List<Log>> logsByWeekPeriod
                = getLogsForPendingApprovalTimesheetsGroupByWeekPeriod(
                employee.getAssignments());
        return logsByWeekPeriod.entrySet().stream()
                .map(item -> {
                    TimesheetDto timesheetDto = new TimesheetDto();
                    timesheetDto.setFromDate(item.getKey().getStartWeek());
                    timesheetDto.setToDate(item.getKey().getEndWeek());
                    timesheetDto.setLogs(getLogsDtoFromLogs(item.getValue()));
                    timesheetDto
                            .setPlanned(Double.valueOf(employee.getWorkLoad()));
                    timesheetDto
                            .setActual(getActualWorkloadByLogs(
                                    item.getValue()));
                    timesheetDto
                            .setProjects(getProjectItemsForTimesheets(
                                    employee.getAssignments(),
                                    item.getValue()));
                    return timesheetDto;
                })
                .collect(Collectors.toList());
    }

    private TimesheetDto getTimesheetCurrentWeek(Employee employee) {
        TimesheetDto currentWeekTimesheet = new TimesheetDto();
        List<Log> currentWeekLogs
                = getCurrentWeekLogs(employee.getAssignments());
        List<LogDto> currentWeekLogsDto = getLogsDtoFromLogs(currentWeekLogs);
        currentWeekTimesheet.setLogs(currentWeekLogsDto);
        currentWeekTimesheet
                .setPlanned(Double.valueOf(employee.getWorkLoad()));
        currentWeekTimesheet
                .setActual(getActualWorkloadByLogs(currentWeekLogs));
        currentWeekTimesheet
                .setProjects(getProjectItemsForTimesheets(
                        employee.getAssignments(),
                        currentWeekLogs));
        return currentWeekTimesheet;
    }

    private List<TimesheetProjectItemDto> getProjectItemsForTimesheets(
            List<Assignment> assignments,
            List<Log> logs
    ) {
        return assignments.stream()
                .map(assignment -> {
                    TimesheetProjectItemDto projectItem
                            = new TimesheetProjectItemDto();
                    projectItem.setName(assignment.getProject().getName());
                    projectItem.setColor(assignment.getProject().getColor());
                    projectItem.setPlanned(Double
                            .valueOf(assignment.getWorkLoad()));
                    projectItem.setActual(getActualWorkloadByLogs(logs));
                    return projectItem;
                }).collect(Collectors.toList());
    }

    private Double getActualWorkloadByLogs(List<Log> logs) {
        return logs.stream().mapToDouble(Log::getTime).sum();
    }

    private List<LogDto> getLogsDtoFromLogs(List<Log> logs) {
        return logs.stream()
                .map(log -> {
                    LogDto logDto = new LogDto();
                    logDto.setColor(log
                            .getAssignment()
                            .getProject().getColor());
                    logDto.setComment(log.getComment());
                    logDto.setTime(log.getTime());
                    return logDto;
                }).collect(Collectors.toList());
    }

    private Map<WeekPeriodUtil, List<Log>>
    getLogsForPendingApprovalTimesheetsGroupByWeekPeriod(
            List<Assignment> assignments
    ) {
        List<Log> logs = assignments.stream()
                .flatMap(assignment -> assignment.getLogs().stream()
                        .filter(log -> log
                                .getDate()
                                .before(currentWeekPeriod
                                        .getStartWeek())))
                .collect(Collectors.toList());
        return logs.stream().collect(
                Collectors.groupingBy(log ->
                        new WeekPeriodUtil(log.getDate())));
    }

    private List<Log> getCurrentWeekLogs(List<Assignment> assignments) {
        return assignments.stream()
                .flatMap(assignment -> assignment.getLogs().stream()
                        .filter(log ->
                                log.getDate()
                                        .after(currentWeekPeriod
                                                .getStartWeek())
                                        && log.getDate()
                                        .before(currentWeekPeriod
                                                .getEndWeek())))
                .collect(Collectors.toList());
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
                            .setActual(getActualWorkloadByLogs(
                                    getCurrentWeekLogs(
                                            employee.getAssignments())
                            ));
                    employeesPageItem.setStatus(employee.getStatus());
                    employeesPageItem.setPendingApprovalDtoList(
                            getTimesheetItemsForEmployeesPage(employee)
                    );
                    return employeesPageItem;
                }).collect(Collectors.toList());
        employeesPageDto.setEmployeeItems(items);
        return employeesPageDto;
    }

    private List<TimesheetItemsDto> getTimesheetItemsForEmployeesPage(
            Employee employee
    ) {
        Map<WeekPeriodUtil, List<Log>> logsByWeekPeriod
                = getLogsForPendingApprovalTimesheetsGroupByWeekPeriod(
                employee.getAssignments());
        return logsByWeekPeriod.entrySet().stream()
                .map(item -> {
                    TimesheetItemsDto timesheetItem = new TimesheetItemsDto();
                    timesheetItem.setFromDate(item.getKey().getStartWeek());
                    timesheetItem.setToDate(item.getKey().getEndWeek());
                    timesheetItem.setPlanned(Double
                            .valueOf(employee.getWorkLoad()));
                    timesheetItem.setActual(
                            getActualWorkloadByLogs(item.getValue()));
                    return timesheetItem;
                }).collect(Collectors.toList());
    }

    public ByteArrayOutputStream getEmployeesPdf() {
        EmployeesPageDto employeesPageDto = getAll();
        return pdfService.generate(employeesPageDto);
    }

    public ByteArrayOutputStream getEmployeeProfilePdf(int id) {
        EmployeeProfileDto employeeProfileDto = get(id);
        return pdfService.generate(employeeProfileDto);
    }
}
