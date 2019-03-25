package app.resources;

import app.Services.JDBCConnection;
import app.dao.impl.NotificationDao;
import app.entities.Database;
import app.entities.ProjectVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/jdbc")
public class JDBCResource {

    @Autowired
    JDBCConnection jdbcConnection;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Database getProjectVersion() {
        return jdbcConnection.getDatabase();
    }
}
