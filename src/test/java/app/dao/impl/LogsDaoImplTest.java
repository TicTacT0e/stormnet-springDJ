package app.dao.impl;

import app.config.beans.DaoConfig;
import app.config.beans.HibernateConfig;
import app.config.beans.PropertyConfig;
import app.dao.BasicCrudDao;
import app.entities.Log;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertyConfig.class, DaoConfig.class, HibernateConfig.class})
public class LogsDaoImplTest {

    DateTime customDateTimeFromString = new DateTime("2018-05-05T10:11:12.123");

    @Autowired
    BasicCrudDao <Log> l;

 /*   @Test
    public void testCreate() {
        Log logs = new Log(1,2.0,3,"srgsg",customDateTimeFromString);
         l.create(logs);
        //Log logs = new Log();
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