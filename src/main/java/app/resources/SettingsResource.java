package app.resources;

import app.dao.BasicCrudDao;
import app.entities.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

@Component
public class SettingsResource {

    @Autowired
    BasicCrudDao<Settings> settingsDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
}
