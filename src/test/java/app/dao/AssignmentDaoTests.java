package app.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AssignmentDaoTests {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static final String SCHEMA = "timesheet_dev";

    private IDatabaseTester databaseTester;


    @Before
    public void setUp() throws Exception {
        databaseTester = new MySqlDatabaseTester(DRIVER, URL, USERNAME, PASSWORD, SCHEMA);

        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getClassLoader().getResourceAsStream("initial-dataset.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    @Test
    public void initialTest() {
        try {
            System.out.println(databaseTester.getDataSet().getTable("Assignment").getRowCount());
            Assert.assertNotNull(databaseTester.getDataSet().getTable("Assignment"));

            IDataSet actualDataSet = databaseTester.getDataSet();
            ITable actualTable = actualDataSet.getTable("Assignment");


            IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                    .build(getClass()
                            .getClassLoader()
                            .getResourceAsStream("expected.xml"));
            ITable expectedTable = expectedDataSet.getTable("Assignment");

            Assertion.assertEquals(expectedTable, actualTable);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void insertTest() {
        try {
            IDataSet dataSet = new FlatXmlDataSetBuilder()
                    .build(getClass().getClassLoader().getResourceAsStream("someSet.xml"));

            DatabaseOperation.INSERT.execute(databaseTester.getConnection(), dataSet);
            IDataSet actualDataSet = databaseTester.getConnection().createDataSet();
            ITable actualTable = actualDataSet.getTable("Assignment");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                    .build(getClass()
                            .getClassLoader()
                            .getResourceAsStream("expected.xml"));
            ITable expectedTable = expectedDataSet.getTable("Assignment");

            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
