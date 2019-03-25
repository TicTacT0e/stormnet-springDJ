package app.resources;

import app.Services.JDBCConnection;
import app.dao.impl.NotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/jdbc")
public class JDBCResource {


    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_HTML)
    public void getconnection() {
        JDBCConnection.getConnection();
    }
}
