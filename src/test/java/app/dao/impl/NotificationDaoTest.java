package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Notification;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;


public class NotificationDaoTest extends ConnectionForTests {


    @Autowired
    private BasicCrudDao<Notification> notificationDao;

    private String table = "Notification";
    private String[] columnsToIgnore = {"createdAt"};


    public NotificationDaoTest() {
        super("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\input.xml");
    }

    @Test
    public void testFindAll() throws SQLException, DatabaseUnitException, FileNotFoundException {
        List<Notification> notifications = notificationDao.findAll();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\input.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedDataSet.getTable(table).getRowCount(), notifications.size());
    }


    @Test
    public void findById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        Notification notification = notificationDao.findById(2);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\input.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        String expectedTitleOfNotification = (String) expectedDataSet.getTable(table).getValue(1, "title");
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedTitleOfNotification, notification.getTitle());
    }

    @Test
    public void testCreate() throws SQLException, DatabaseUnitException, FileNotFoundException {
        notificationDao.create(new Notification(4, 3, "status4", "title4", "description4", "link4"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\createExpected.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

    @Test
    public void testDeleteById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        notificationDao.deleteById(2);
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\deleteExpected.xml"));
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testDeleteEntity() throws SQLException, FileNotFoundException, DatabaseUnitException {
        notificationDao.delete(new Notification(2, 2, "status2", "title2", "description2", "link2"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\deleteExpected.xml"));
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testUpdate() throws SQLException, FileNotFoundException, DatabaseUnitException {
        notificationDao.update(new Notification(1, 3, "updatedStatus", "updatedTitle", "updatedDescription", "updatedLink"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\notificationDataSet\\updateExpected.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

}
