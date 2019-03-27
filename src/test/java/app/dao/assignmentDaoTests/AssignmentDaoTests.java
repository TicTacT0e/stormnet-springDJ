package app.dao.assignmentDaoTests;

import app.config.beans.DaoConfig;
import app.dao.AssignmentDao;
import app.dao.MySqlDatabaseTester;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class AssignmentDaoTests {

    private String assignmentTable;

    private IDatabaseTester databaseTester;

    @Autowired
    AssignmentDao assignmentDao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream("assignmentTest.properties")));

        String driver = properties.getProperty("db-driver");
        String url = properties.getProperty("db-url");
        String username = properties.getProperty("db-username");
        String password = properties.getProperty("db-password");
        String assignmentSchema = properties.getProperty("assignment-schema");
        assignmentTable = properties.getProperty("assignment-table");

        databaseTester = new MySqlDatabaseTester(driver, url, username, password, assignmentSchema);

        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("initial-dataset.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    @Test
    public void setUpDatabaseTest() {
        try {
            Assert.assertNotNull(databaseTester.getDataSet().getTable(assignmentTable));
            IDataSet actualDataSet = databaseTester.getDataSet();
            ITable actualTable = actualDataSet.getTable(assignmentTable);
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                    .build(getClass()
                            .getClassLoader()
                            .getResourceAsStream("initial-dataset.xml"));
            ITable expectedTable = expectedDataSet.getTable(assignmentTable);
            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
