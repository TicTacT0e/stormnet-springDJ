package app.dao.impl;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProjectDaoImplTest {

    private static IDatabaseConnection connection = null;

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/timesheet_dev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "__chu2552");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeClass
    public static void setUp() throws Exception {
        // initialize your database connection here
        connection = new MySqlConnection(getConnection(), "timesheet_dev");
        // ...

        // initialize your dataset here
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("D:\\Alena\\J2EE_projects\\TimesheetManagement\\src\\test\\resources\\app\\dao\\impl\\inputDb.xml"));

        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void getAll() {
        Assert.assertTrue(true);
    }

    @Test
    public void findById() throws SQLException, DatabaseUnitException, FileNotFoundException{
               //Assert.assertTrue(true);
        IDataSet tmpDataset = connection.createDataSet();

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream("D:\\Alena\\J2EE_projects\\TimesheetManagement\\src\\test\\resources\\findResult.xml"));

        Assertion.assertEquals(expectedDataSet, tmpDataset);
    }

    @Test
    public void testSave() throws SQLException, DatabaseUnitException, FileNotFoundException {
        IDataSet tmpDataset = connection.createDataSet();

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream("D:\\Alena\\J2EE_projects\\TimesheetManagement\\src\\test\\resources\\app\\dao\\impl\\inputDbExpected.xml"));

        Assertion.assertEquals(expectedDataSet, tmpDataset);
    }

    @Test
    public void delete() {
        Assert.assertTrue(true);
    }

    @Test
    public void delete1() {
        Assert.assertTrue(true);
    }

    @Test
    public void edit() {
        Assert.assertTrue(true);
    }
}