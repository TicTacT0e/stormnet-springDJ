package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Timesheet;
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

public class TimesheetDaoTest extends ConnectionForTests {

    @Autowired
    private BasicCrudDao<Timesheet> basicCrudDao;

    private String table = "Timesheet";
    private String[] columnsToIgnore = {"timesheetJson"};

    public TimesheetDaoTest() {
        super("src\\test\\resources\\app\\dao\\impl\\timesheetDataSet\\initialDataset.xml");
    }


    @Test
    public void testFindAll() throws SQLException, DatabaseUnitException, FileNotFoundException {
        List<Timesheet> timesheets = basicCrudDao.findAll();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\timesheetDataSet\\initialDataset.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedDataSet.getTable(table).getRowCount(), timesheets.size());
    }


    @Test
    public void findById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        Timesheet timesheet = basicCrudDao.findById(2);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\timesheetDataSet\\initialDataset.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        String expectedperiodId = (String) expectedDataSet.getTable(table).getValue(1, "periodId");
        String actualPeriodId = String.valueOf(timesheet.getPeriodId());
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedperiodId, actualPeriodId);
    }

    @Test
    public void testCreate() throws SQLException, DatabaseUnitException, FileNotFoundException {
        basicCrudDao.create(new Timesheet(5, 5, null, "ok"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\timesheetDataSet\\saveDataset.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

    @Test
    public void testDeleteById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        basicCrudDao.deleteById(3);
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\timesheetDataSet\\deleteDataset.xml"));
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testUpdate() throws SQLException, FileNotFoundException, DatabaseUnitException {
        basicCrudDao.update(new Timesheet(3, 8, null, "notOk"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src\\test\\resources\\app\\dao\\impl\\timesheetDataSet\\updateDataset.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

}
