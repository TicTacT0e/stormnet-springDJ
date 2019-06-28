package app.resources;

import app.dao.BasicCrudDao;
import app.dto.EmployeesPageDto;
import app.dto.EmployeesPageItemDto;
import app.entities.Assignment;
import app.entities.Employee;
import app.entities.Log;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/employee")
public class EmployeeResource {

    @Autowired
    private BasicCrudDao<Employee> employeeDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeesPageDto getAll() {
        return getEmployeesPageDto();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee get(@PathParam("id") int id) {
        return employeeDao.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Employee employee) {
        employeeDao.create(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Employee employee) {
        employeeDao.update(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, Employee employee) {
        employeeDao.delete(employee);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    private EmployeesPageDto getEmployeesPageDto() {
        EmployeesPageDto employeesPageDto = new EmployeesPageDto();
        List<Employee> employees = employeeDao.findAll();

        List<EmployeesPageItemDto> items = employees.stream()
                .map(employee -> {
                    EmployeesPageItemDto employeesPageItemDto
                            = new EmployeesPageItemDto();
                    employeesPageItemDto.setName(employee.getUser().getName());
                    employeesPageItemDto.setPhotoUrl(employee.getUser().getPhotoUrl());
                    employeesPageItemDto.setRole(employee.getRole().getName());
                    employeesPageItemDto.setPlanned(Double.valueOf(employee.getWorkLoad()));
                    employeesPageItemDto
                            .setActual(getActualEmployeeWorkloadThisWeek(employee));
                    employeesPageItemDto.setStatus(employee.getStatus());
                    return employeesPageItemDto;
                }).collect(Collectors.toList());

        employeesPageDto.setEmployeeItems(items);
        return employeesPageDto;
    }

    private Double getActualEmployeeWorkloadThisWeek(Employee employee) {
        List<Assignment> assignments = employee.getAssignments();

        final Date startWeekDate = DateTime.now()
                .withDayOfWeek(DateTimeConstants.MONDAY)
                .withTimeAtStartOfDay().toDate();
        final Date endWeekDate = DateTime.now()
                .withDayOfWeek(DateTimeConstants.SUNDAY)
                .withTimeAtStartOfDay().toDate();

        List<Double> actualWorkLoadByAssignments
                = assignments.stream()
                .map(assignment ->
                    assignment.getLogs().stream()
                            .filter(log -> log.getDate().after(startWeekDate)
                            && log.getDate().before(endWeekDate))
                            .mapToDouble(Log::getTime).sum()
                ).collect(Collectors.toList());
        return actualWorkLoadByAssignments.stream()
        .mapToDouble(Double::doubleValue).sum();
    }
}
