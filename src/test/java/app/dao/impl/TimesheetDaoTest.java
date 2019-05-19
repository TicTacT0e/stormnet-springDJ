/*
package app.dao.impl;

import app.config.ApplicationInitializer;
import app.config.beans.DaoConfig;
import app.config.beans.HibernateConfig;
import app.config.beans.PropertyConfig;
import app.dao.BasicCrudDao;
import app.entities.Timesheet;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationInitializer.class, PropertyConfig.class, HibernateConfig.class, DaoConfig.class})
public class TimesheetDaoTest {

    private static IDatabaseConnection connection;

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Autowired
    private BasicCrudDao<Timesheet> basicCrudDao;

    private String table = "Timesheet";
    private String[] columnsToIgnore = {"timesheetJson"};

    private Connection getConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Before
    public void setUp() throws Exception {
        connection = new MySqlConnection(getConnection(), "timesheet_dev");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("timesheetDataSet\\initialDataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @After
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testFindAll() throws SQLException, DatabaseUnitException, FileNotFoundException {
        List<Timesheet> timesheets = basicCrudDao.findAll();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("timesheetDataSet\\initialDataset.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedDataSet.getTable(table).getRowCount(), timesheets.size());
    }


    @Test
    public void findById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        Timesheet timesheet = basicCrudDao.findById(2);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("timesheetDataSet\\initialDataset.xml"));
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
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("timesheetDataSet\\saveDataset.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

    @Test
    public void testDeleteById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        basicCrudDao.deleteById(3);
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("timesheetDataSet\\deleteDataset.xml"));
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testUpdate() throws SQLException, FileNotFoundException, DatabaseUnitException {
        basicCrudDao.update(new Timesheet(3, 8, null, "notOk"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("timesheetDataSet\\updateDataset.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

}
*/
