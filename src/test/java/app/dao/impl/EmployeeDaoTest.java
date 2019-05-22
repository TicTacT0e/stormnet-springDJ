package app.dao.impl;

import app.config.ApplicationInitializer;
import app.config.beans.DaoConfig;
import app.config.beans.HibernateConfig;
import app.config.beans.PropertyConfig;
import app.dao.BasicCrudDao;
import app.entities.Employee;
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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationInitializer.class, PropertyConfig.class, HibernateConfig.class, DaoConfig.class})
public class EmployeeDaoTest {
    private static IDatabaseConnection connection;

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Autowired
    private BasicCrudDao<Employee> employeeBasicCrudDao;

    private String table = "employee_1";
    private String[] columnsToIgnore = {"email"};

    private Connection getConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Before
    public void setUp() throws Exception {
        connection = new MySqlConnection(getConnection(), "timesheet_dev");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\input.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @After
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testFindAll() throws SQLException, DatabaseUnitException, FileNotFoundException {
        List<Employee> employees = employeeBasicCrudDao.findAll();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\input.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedDataSet.getTable(table).getRowCount(), employees.size());
    }


    @Test
    public void findById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        Employee employee = employeeBasicCrudDao.findById(2);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\input.xml"));
        IDataSet actualDataSet = connection.createDataSet();
        String expectedEmailOfEmployee = (String) expectedDataSet.getTable(table).getValue(1, "max@mail.ru");
        Assertion.assertEquals(expectedDataSet, actualDataSet);
        Assert.assertEquals(expectedEmailOfEmployee, employee.getEmail());
    }

    @Test
    public void testCreate() throws SQLException, DatabaseUnitException, FileNotFoundException {
        employeeBasicCrudDao.create(new Employee(4, "Alex", "AlexPhotoUrl", "alex@gmail.by"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\createExpected.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }

    @Test
    public void testDeleteById() throws SQLException, FileNotFoundException, DatabaseUnitException {
        employeeBasicCrudDao.deleteById(2);
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\deleteExpected.xml"));
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testDeleteEntity() throws SQLException, FileNotFoundException, DatabaseUnitException {
        employeeBasicCrudDao.delete(new Employee(2, "Sergey", "SergeyPhotoUrl", "sergey@mail.ru"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\deleteExpected.xml"));
        Assertion.assertEquals(expectedDataSet, actualDataSet);
    }

    @Test
    public void testUpdate() throws SQLException, FileNotFoundException, DatabaseUnitException {
        employeeBasicCrudDao.update(new Employee(1,"Max", "MaxPhotoUrl", "max@mail.ru"));
        IDataSet actualDataSet = connection.createDataSet();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("employeeDataSet\\updateExpected.xml"));
        Assertion.assertEqualsIgnoreCols(expectedDataSet, actualDataSet, table, columnsToIgnore);
    }
}
