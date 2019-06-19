package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Notification;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;


public class NotificationDaoTest extends ConnectionForTests {


	@Autowired
	private BasicCrudDao<Notification> notificationDao;

	private String table = "Notification";
	private String[] columnsToIgnore = {"createdAt"};


	public NotificationDaoTest() {
		super("app/dao/impl/notificationDataSet/input.xml");
	}

	@Test
	public void testFindAll() throws SQLException, DatabaseUnitException {
		List<Notification> notifications = notificationDao.findAll();
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/notificationDataSet/input.xml")).getTable(table);
		ITable actualDataSet = connection.createDataSet().getTable(table);
		Assertion.assertEquals(expectedDataSet, actualDataSet);
		Assert.assertEquals(expectedDataSet.getRowCount(), notifications.size());
	}


	@Test
	public void findById() throws SQLException, DatabaseUnitException {
		Notification notification = notificationDao.findById(2);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/notificationDataSet/input.xml")).getTable(table);
		ITable actualDataSet = connection.createDataSet().getTable(table);
		String expectedTitleOfNotification = (String) expectedDataSet.getValue(1, "title");
		Assertion.assertEquals(expectedDataSet, actualDataSet);
		Assert.assertEquals(expectedTitleOfNotification, notification.getTitle());
	}

	@Test
	public void testCreate() throws SQLException, DatabaseUnitException {
		notificationDao.create(new Notification(4, 3, "status4", "title4", "description4", "link4"));
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/notificationDataSet/createExpected.xml")).getTable(table);
		Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, columnsToIgnore);
	}

	@Test
	public void testDeleteById() throws SQLException, DatabaseUnitException {
		notificationDao.deleteById(2);
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/notificationDataSet/deleteExpected.xml")).getTable(table);
		Assertion.assertEquals(expectedDataSet, actualDataSet);
	}

	@Test
	public void testDeleteEntity() throws SQLException, DatabaseUnitException {
		notificationDao.delete(new Notification(2, 2, "status2", "title2", "description2", "link2"));
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/notificationDataSet/deleteExpected.xml")).getTable(table);
		Assertion.assertEquals(expectedDataSet, actualDataSet);
	}

	@Test
	public void testUpdate() throws SQLException, DatabaseUnitException {
		notificationDao.update(new Notification(1, 3, "updatedStatus", "updatedTitle", "updatedDescription", "updatedLink"));
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/notificationDataSet/updateExpected.xml")).getTable(table);
		Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, columnsToIgnore);
	}

}
