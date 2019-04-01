package app.dao;

import app.dao.impl.AssignmentDaoImpl;
import app.entities.Assignment;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class AssignmentTestTwo extends DBTestCase {

    private AssignmentDao assignmentDao = new AssignmentDaoImpl();

    public AssignmentTestTwo(String name) throws Exception {
        super(name);
        Properties properties = new Properties();
        properties.load(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream("assignmentTests.properties")));

        String driver = properties.getProperty("db-driver");
        String url = properties.getProperty("db-url");
        String username = properties.getProperty("db-username");
        String password = properties.getProperty("db-password");
        String assignmentSchema = properties.getProperty("assignment-schema");

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                driver);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                url);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                username);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                password);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
                assignmentSchema);

        /*
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new MySqlDataTypeFactory());
        dbConfig.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER,
                new MySqlMetadataHandler());
                */
        /*
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection jdbcConnection
                    = DriverManager.getConnection(url + '/' + assignmentSchema, username,
                    password);
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }

    protected IDatabaseConnection getMySqlConnection() throws Exception {
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new MySqlDataTypeFactory());
        dbConfig.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER,
                new MySqlMetadataHandler());
        return connection;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
//        return new FlatXmlDataSetBuilder()
//                .build(new FileInputStream("/home/tictactoe/Documents/STORMNET/" +
//                        "timesheets/TimesheetManagement/src/test/java/" +
//                        "resources/initial-dataset.xml"));
        return new FlatXmlDataSetBuilder()
                .build(getClass().getClassLoader().getResourceAsStream("initial-dataset.xml"));
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
        IDatabaseConnection connection = getMySqlConnection();
        DatabaseOperation.CLEAN_INSERT.execute(connection, data);
    }

    @Test
    public void testBy() {
        int i = 4;
        Assert.assertEquals(0, i);
    }

    @Test
    public void testInit() throws Exception {
        IDataSet expectedD = new FlatXmlDataSetBuilder()
                .build(getClass().getClassLoader().getResourceAsStream("initial-dataset.xml"));
        ITable expectedT = expectedD.getTable("Assignment");

        IDataSet actualD = getMySqlConnection().createDataSet();
        ITable actualT = actualD.getTable("Assignment");

        Assertion.assertEquals(expectedT, actualT);
    }

    @Test
    public void testInsrt() throws Exception {
        IDataSet expectedD = new FlatXmlDataSetBuilder()
                .build(getClass().getClassLoader().getResourceAsStream("saveDataSets/saveDataSet"));
        ITable expectedT = expectedD.getTable("Assignment");

        Assignment assignment =
                new Assignment(4, 7, 12000);
        assignmentDao.save(assignment);

        IDataSet actualD = getMySqlConnection().createDataSet();
        ITable actualT = actualD.getTable("Assignment");
        Assertion.assertEquals(expectedT, actualT);

    }
}
