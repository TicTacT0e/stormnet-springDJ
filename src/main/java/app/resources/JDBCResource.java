package app.resources;

import app.Services.JDBCService;
import app.dao.impl.NotificationDao;
import app.entities.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/jdbc")
public class JDBCResource {

    @Autowired
    JDBCService jdbcService;

    @Autowired
    NotificationDao notificationDao;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Database getDatabase() {
        return jdbcService.getDatabase();
    }

    @GET
    @Path("/g")
    @Produces(MediaType.TEXT_HTML)
    public String getAll() {
        return notificationDao.getAll();
    }
}
