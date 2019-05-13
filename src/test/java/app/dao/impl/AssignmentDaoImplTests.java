package app.dao.impl;

import app.config.beans.DaoConfig;
import app.config.beans.HibernateConfig;
import app.config.beans.PropertyConfig;
import app.dao.BasicCrudDao;
import app.entities.Assignment;
import app.exceptions.EntityNotFoundException;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class, HibernateConfig.class, PropertyConfig.class})
public class AssignmentDaoImplTests extends DBTestCase {


    @Autowired
    private BasicCrudDao<Assignment> assignmentDao;

    private String driver;
    private String url;
    private String username;
    private String password;
    private String assignmentSchema;
    private String assignmentTable;

    private static final int NUMBER_OF_FIRST_ROW = 0;

    public AssignmentDaoImplTests() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResourceAsStream("assignment/assignment.properties")));
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

    private IDatabaseConnection getMySqlConnection() throws Exception {
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

    @Test
    public void deleteById() throws Exception {
        assignmentDao.deleteById(5);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/delete-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteWithNonExistsPrimaryKey() {
        assignmentDao.deleteById(100);
    }

    @Test
    public void edit() throws Exception {
        Assignment assignment = new Assignment(5, 5, 1, 1, 10000);
        assignmentDao.update(assignment);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/edit-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void editWithNonExistsPrimaryKey() {
        assignmentDao
                .update(new Assignment(5, 100, 100, 100, 15000));
    }

    @Test
    public void findById() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/find-by-id-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);

        Assignment assignment = assignmentDao.findById(4);

        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "id")
                        .toString(),
                String.valueOf(assignment.getId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "projectId")
                        .toString(),
                String.valueOf(assignment.getProjectId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "employeeId")
                        .toString(),
                String.valueOf(assignment.getEmployeeId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "activityId")
                        .toString(),
                String.valueOf(assignment.getActivityId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "workLoad")
                        .toString(),
                String.valueOf(assignment.getWorkLoad()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdWithNonExistsPrimaryKey() {
        assignmentDao.findById(100);
    }

    @Test
    public void getAll() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/initial-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        List<Assignment> assignments = assignmentDao.findAll();
        int index = NUMBER_OF_FIRST_ROW;
        for (Assignment assignment : assignments) {
            Assert.assertEquals(expectedTable.getValue(index, "id")
                            .toString(),
                    String.valueOf(assignment.getId()));
            Assert.assertEquals(expectedTable.getValue(index, "projectId")
                            .toString(),
                    String.valueOf(assignment.getProjectId()));
            Assert.assertEquals(expectedTable.getValue(index, "employeeId")
                            .toString(),
                    String.valueOf(assignment.getEmployeeId()));
            Assert.assertEquals(expectedTable.getValue(index, "activityId")
                            .toString(),
                    String.valueOf(assignment.getActivityId()));
            Assert.assertEquals(expectedTable.getValue(index, "workLoad")
                            .toString(),
                    String.valueOf(assignment.getWorkLoad()));
            index++;
        }
    }

    @Test
    public void save() throws Exception {
        Assignment assignment =
                new Assignment(6, 5, 1, 5, 10000);
        assignmentDao.create(assignment);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/save-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveAlreadyExistsEntity() {
        assignmentDao
                .create(new Assignment(4, 2, 1, 1, 4500));
    }
}
