package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Log;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class LogsDaoImplTest extends ConnectionForTests {

    @Autowired
    private BasicCrudDao<Log> logDao;

    private String table = "Logs";
    private String[] columnsToIgnore = {"date", "id"};

    public LogsDaoImplTest() {
        super("app/dao/impl/logDataSet/input.xml");
    }

    @Test
    public void testFindAll() throws SQLException, DatabaseUnitException {
        List<Log> logs = logDao.findAll();
        ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("app/dao/impl/logDataSet/input.xml")).getTable(table);
        ITable actualDataSet = connection.createDataSet().getTable(table);
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedDataSet.getRowCount(), logs.size());
    }

    @Test
    public void testfindById() throws SQLException, DatabaseUnitException {
        Log log = logDao.findById(1);
        ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("app/dao/impl/logDataSet/input.xml")).getTable(table);
        ITable actualDataSet = connection.createDataSet().getTable(table);
        String expectedTimeOfLog = (String) expectedDataSet.getValue(0, "comment");
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedTimeOfLog, log.getComment());
    }

    @Test
    public void testCreate() throws SQLException, DatabaseUnitException {
        logDao.create(new Log(4, 4, 4, 4, "comment4", 0));
        ITable actualDataSet = connection.createDataSet().getTable(table);
        ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("app/dao/impl/logDataSet/createExpected.xml")).getTable(table);
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, columnsToIgnore);
    }

    @Test
    public void testDeleteById() throws SQLException, DatabaseUnitException {
        logDao.deleteById(2);
        ITable actualDataSet = connection.createDataSet().getTable(table);
        ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("app/dao/impl/logDataSet/deleteExpected.xml")).getTable(table);
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testDeleteEntity() throws SQLException, DatabaseUnitException {
        logDao.delete(new Log(2, 2, 2, 2, "comment2", 0));
        ITable actualDataSet = connection.createDataSet().getTable(table);
        ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader().getResourceAsStream("app/dao/impl/logDataSet/deleteExpected.xml")).getTable(table);
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testUpdate() throws SQLException, DatabaseUnitException {
        logDao.update(new Log(3, 10, 10, 10, "comment10", 0));
        ITable actualDataSet = connection.createDataSet().getTable(table);
        ITable expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("app/dao/impl/logDataSet/updateExpected.xml")).getTable(table);
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, columnsToIgnore);
    }
}