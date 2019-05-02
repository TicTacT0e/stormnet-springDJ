package app.dbUnitTest;

import app.dao.impl.LogsDaoImpl;
import app.entities.Log;
import org.dbunit.Assertion;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

//**************************************************
public class TestDataBase {
    private LogsDaoImpl logsDao = new LogsDaoImpl();
    private FlatXmlDataSet loadedDataSet;
    private Connection jdbcConnection;
    //private IDatabaseConnection DBconnection = null;
    private static IDatabaseConnection connection = null;

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/timesheetmanager?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "Kraskovski K30197");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeClass
    public static void setUp() throws Exception {
        // initialize your database connection here
        connection = new MySqlConnection(getConnection(), "timesheetmanager");
        // ...

        // initialize your dataset here
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("D:\\DiskD\\JAVA\\Project_TimeSheetManagment\\TimesheetManagement\\src\\test\\resources\\input.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Log> list = logsDao.findAll();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream("D:\\DiskD\\JAVA\\Project_TimeSheetManagment\\TimesheetManagement\\src\\test\\resources\\input.xml"));
        ;
        ITable expectedData = expectedDataSet.getTable("logs");
        IDataSet actualDataSet = connection.createDataSet();
         ITable actualData = actualDataSet.getTable("logs");
        Assertion.assertEquals(expectedData, actualData);
    }

/*    @Test
    public void testSave() throws Exception {
        LogsDaoImpl logsD = new LogsDaoImpl();
        //logsD.getAll();
        IDataSet expectedDataSet = getDataSet();
        ITable expectedData = expectedDataSet.getTable("logs");
        IDataSet actualDataSet = DBconnection.createDataSet();
        ITable actual = expectedDataSet.getTable("logs");
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(actual, expectedData, ignore);*/
/*
        Person person = new Person();
        person.setName("Lilia");
        person.setSurname("Vernugora");

        service.save(person);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/devcolibri/entity/person/person-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "person", ignore);


        Assertion.assertEquals(actualDataSet,);*/
    }


        /*
        new DbUnitAssert().assertEquals(new FlatXmlDataSetBuilder()
                .build(new StringReader("<projectId><employeeId><time><comment><date>")),
                dataset.get());
        try (HibernateTransaction tx = txutils.start().withAutoCommit()) {
            dao.save(new SimpleEntity(1, "alice", new GroupEntity(1), new GroupEntity(2)));
            dao.save(new SimpleEntity(2, "bob"));
            dao.save(new SimpleEntity(3, "carol"));
            dao.save(new SimpleEntity(4, "dave"));
        }

        // DB should now have 4 rows
        FlatXmlDataSet expected = new FlatXmlDataSetBuilder().build(this.getClass()
                .getResourceAsStream("/com/peterphi/std/guice/hibernatetest/alice-bob-carol-dave-dataset.xml"));

        new DbUnitAssert().assertEquals(expected, dataset.get());

    }*/
