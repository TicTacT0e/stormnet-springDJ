package app.dao.impl;

import app.config.beans.DaoConfig;
import app.config.beans.PropertyConfig;
import app.dao.LogsDao;
import app.entities.Logs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertyConfig.class, DaoConfig.class})
public class LogsDaoImplTest {

    @Autowired
    LogsDao l;

    @Test
    public void delete() {

        // l.save(new Logs(1,2,3.0,"srgsg",new Date(Calendar.getInstance().getTimeInMillis())));
        //Logs logs = new Logs();

        l.delete(13);
    }
    @Test
    public void save(){
        l.save(new Logs(14,1,1.0,"srgfeaf",new Date(Calendar.getInstance().getTimeInMillis())));
    }
}