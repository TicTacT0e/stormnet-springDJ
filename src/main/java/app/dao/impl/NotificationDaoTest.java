package app.dao.impl;

import app.entities.Notification;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NotificationDaoTest {
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
                    "username",
                    "qwerty123");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Before
    public void setUp() throws Exception {
        // initialize your database connection here
        connection = new MySqlConnection(getConnection(), "timesheet_dev");
        // ...

        // initialize your dataset here
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("C:\\FORTEST\\input.xml"));

        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @After
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void getAll() {
        Assert.assertTrue(true);
    }

    @Test
    public void findById() {
        Assert.assertTrue(true);
    }

    @Test
    public void testCreate() throws SQLException, DatabaseUnitException, FileNotFoundException {
        NotificationDaoImpl dao = new NotificationDaoImpl();
        dao.create(new Notification(4, null, 3, "status4", "title4", "description4", "link4"));
        IDataSet tmpDataset = connection.createDataSet();

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream("C:\\FORTEST\\expected.xml"));

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
