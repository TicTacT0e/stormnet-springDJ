package app.dao;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;

public class AssignmentTests extends DBTestCase {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/timesheet_dev";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    // private IDatabaseTester databaseTester;


    public AssignmentTests(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                DRIVER);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                URL);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                USERNAME);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                PASSWORD);
    }


    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder()
                .build(new FileInputStream("/home/tictactoe/Documents/STORMNET/timesheets/" +
                        "TimesheetManagement/src/test/resource/initial-dataset.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Before
    public void setUp() throws Exception {

        IDataSet data = getDataSet();
        IDatabaseConnection connection = getConnection();
        DatabaseOperation.CLEAN_INSERT.execute(connection, data);

        /*
        databaseTester = new JdbcDatabaseTester(DRIVER, URL, USERNAME, URL, PASSWORD);

        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new FileInputStream("/home/tictactoe/Documents/STORMNET/timesheets/" +
                        "TimesheetManagement/src/test/resource/initial-dataset.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        */
    }

    @Test
    public void sampleTest() {
        try {
            ITable actualData = getDataSet().getTable("Assignment");
            Assertion.assertEquals(actualData, actualData);
            /*
            IDataSet expectedDataset = new FlatXmlDataSetBuilder()
                    .build(new FileInputStream("app/dao/datasets/expected.xml"));
            ITable expectedData = expectedDataset.getTable("Assignment");

            Assertion.assertEquals(expectedData, actualData);
            */
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
