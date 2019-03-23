package app.resources;

import app.dao.AssigmentDao;
import app.entities.Assigment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/assigment")
public class AssigmentResource {

    @Autowired
    AssigmentDao assigmentDao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assigment> getAll() {
        return assigmentDao.getAll();
    }
}
