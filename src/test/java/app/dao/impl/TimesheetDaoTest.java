package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Timesheet;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class TimesheetDaoTest extends ConnectionForTests {

	@Autowired
	private BasicCrudDao<Timesheet> basicCrudDao;

	private String table = "Timesheet";
	private String[] columnsToIgnore = {"timesheetJson"};

	public TimesheetDaoTest() {
		super("app/dao/impl/timesheetDataSet/initialDataset.xml");
	}


	@Test
	public void testFindAll() throws SQLException, DatabaseUnitException {
		List<Timesheet> timesheets = basicCrudDao.findAll();
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/timesheetDataSet/initialDataset.xml")).getTable(table);
		ITable actualDataSet = connection.createDataSet().getTable(table);
		Assertion.assertEquals(expectedDataSet, actualDataSet);
		Assert.assertEquals(expectedDataSet.getRowCount(), timesheets.size());
	}


	@Test
	public void findById() throws SQLException, DatabaseUnitException {
		Timesheet timesheet = basicCrudDao.findById(2);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/timesheetDataSet/initialDataset.xml")).getTable(table);
		ITable actualDataSet = connection.createDataSet().getTable(table);
		String expectedperiodId = (String) expectedDataSet.getValue(1, "periodId");
		String actualPeriodId = String.valueOf(timesheet.getPeriodId());
		Assertion.assertEquals(expectedDataSet, actualDataSet);
		Assert.assertEquals(expectedperiodId, actualPeriodId);
	}

	@Test
	public void testCreate() throws SQLException, DatabaseUnitException {
		basicCrudDao.create(new Timesheet(5,5, 5, null, "ok"));
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/timesheetDataSet/saveDataset.xml")).getTable(table);
		Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, columnsToIgnore);
	}

	@Test
	public void testDeleteById() throws SQLException, DatabaseUnitException {
		basicCrudDao.deleteById(3);
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/timesheetDataSet/deleteDataset.xml")).getTable(table);
		Assertion.assertEquals(expectedDataSet, actualDataSet);
	}

	@Test
	public void testUpdate() throws SQLException, DatabaseUnitException {
		basicCrudDao.update(new Timesheet(3, 8, 8, null, "notOk"));
		ITable actualDataSet = connection.createDataSet().getTable(table);
		ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
				.getClassLoader()
				.getResourceAsStream("app/dao/impl/timesheetDataSet/updateDataset.xml")).getTable(table);
		Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, columnsToIgnore);
	}

}
