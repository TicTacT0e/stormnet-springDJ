package app.dao.impl.assignment;

import app.config.beans.DaoConfig;
import app.config.beans.HibernateConfig;
import app.config.beans.PropertyConfig;
import app.dao.BasicCrudDao;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class, HibernateConfig.class, PropertyConfig.class})
public class AssignmentDaoTestsInitiator extends DBTestCase {

    @Autowired
    protected BasicCrudDao<Assignment> assignmentDao;

    private String driver;
    private String url;
    private String username;
    private String password;
    private String assignmentSchema;
    protected String assignmentTable;

    public AssignmentDaoTestsInitiator() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResourceAsStream("assignment/assignmentTests.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("db-driver");
        url = properties.getProperty("db-url");
        username = properties.getProperty("db-username");
        password = properties.getProperty("db-password");
        assignmentSchema = properties.getProperty("assignment-schema");
        assignmentTable = properties.getProperty("assignment-table");
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
    }

    @Before
    public void setUp() throws Exception {
        IDataSet data = getDataSet();
        IDatabaseConnection connection = getMySqlConnection();
        DatabaseOperation.CLEAN_INSERT.execute(connection, data);
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
        return new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/initial-dataset.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Test
    public void setUpDatabaseTest() throws Exception {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/initial-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);

        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);

        Assertion.assertEquals(expectedTable, actualTable);
    }
}
