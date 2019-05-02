package app.dao.impl;

import app.config.beans.DaoConfig;
import app.config.beans.HibernateConfig;
import app.config.beans.PropertyConfig;
import app.dao.BasicCrudDao;
import app.dao.LogsDao;
import app.entities.Employee;
import app.entities.Logs;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertyConfig.class, DaoConfig.class, HibernateConfig.class})
public class LogsDaoImplTest {

    DateTime customDateTimeFromString = new DateTime("2018-05-05T10:11:12.123");

    @Autowired
    BasicCrudDao <Logs> l;

 /*   @Test
    public void testCreate() {
        Logs logs = new Logs(1,2.0,3,"srgsg",customDateTimeFromString);
         l.create(logs);
        //Logs logs = new Logs();
        //logs.setTime(1);
      //l.create(logs);
      //  Assert.assertEquals(1,1);
    }*/

@Test
    public void testDelete () {
    l.deleteById(1);
}

@Test
    public void testFindAll() {
    System.out.println(l.findAll());
}

@Test
    public void testFindId(){
    System.out.println(l.findById(2));
}
}