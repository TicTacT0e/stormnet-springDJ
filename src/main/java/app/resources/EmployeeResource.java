package app.resources;

import app.dto.EmployeeProfileDto;
import app.dto.EmployeesPageDto;
import app.entities.Employee;
import app.entities.Role;
import app.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.joda.time.DateTime;
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
import java.io.ByteArrayOutputStream;

@Component
@Path("company/{companyId}/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeesPageDto getAll() {
        return employeeService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeProfileDto get(@PathParam("id") int id) {
        return employeeService.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Employee employee) {
        employeeService.add(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") int id, Employee employee) {
        employeeService.edit(employee);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        employeeService.delete(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @GET
    @Path("/{id}/pdf")
    @Produces("application/pdf")
    public Response getEmployeePdf(@PathParam("id") int id)
            throws JsonProcessingException {
        EmployeeProfileDto employeeProfileDto = employeeService.get(id);
        ObjectMapper objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
        String jsonEmployeeProfile
                = objectMapper.writeValueAsString(employeeProfileDto);
        try {
            Document document = new Document();
            ByteArrayOutputStream byteArrayOutputStream
                    = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph(jsonEmployeeProfile));
            document.close();
            return Response.ok(byteArrayOutputStream.toByteArray(),
                    MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment;"
                            + "filename = "
                            + employeeProfileDto.getClass().getSimpleName()
                            + "_" + DateTime.now().getMillis())
                    .build();
        } catch (DocumentException exception) {
            return Response.serverError().build();
        }
    }

}
