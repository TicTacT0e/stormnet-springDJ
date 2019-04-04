package app.dao.impl;

import app.Services.JDBCConnection;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NotificationDaoImplTest extends JdbcDatabaseTester {

    private NotificationDaoImpl notificationDao;
    JDBCConnection connection = new JDBCConnection();

    public NotificationDaoImplTest() throws ClassNotFoundException {
        super("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/timesheet_dev?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC", "username", "qwerty123", "timesheet_dev");
    }

/*    public NotificationDaoImplTest(String driverClass, String connectionUrl, String username, String password, String schema) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password, schema);
    }*/

/*    public NotificationDaoImplTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/timesheet_dev?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "username");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "qwerty123");
    }*/

    @Before
    public void setUp() throws Exception {
        IDataSet data = getDataSet();
        IDatabaseConnection connection = getConnection();
        //DatabaseOperation.CLEAN_INSERT.execute(connection, data);
    }

    @Test
    public void testCreate() {
        try {
            IDatabaseConnection connection = getConnection();
            NotificationDaoImpl dao = new NotificationDaoImpl();
            dao.findAll();
            
            IDataSet expectedDataSet = getDataSet("./src/test/resources/selectExpected.xml");
            ITable expectedData = expectedDataSet.getTable("notification");

            IDataSet actualDataSet = getConnection().createDataSet();
            ITable actualData = actualDataSet.getTable("notification");

            Assertion.assertEquals(expectedData, actualData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private IDataSet getDataSet(String s) throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(s));
    }

    @After
    public void tearDown() throws Exception {
    }

    public IDataSet getDataSet() {
        try {
            return new FlatXmlDataSetBuilder().build(new FileInputStream("./src/test/resources/input.xml"));
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}