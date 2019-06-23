package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Project;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ProjectDaoImplTest extends ConnectionForTests {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Autowired
    private BasicCrudDao<Project> projectBasicCrudDao;

    private String table = "Projects";
    private String[] columnsToIgnore = {"endDate", "startDate"};

    public ProjectDaoImplTest() {
        super("app/dao/impl/projectDataSet/inputDb.xml");
    }

    @Test
    public void findAllTest() throws SQLException, DatabaseUnitException {
        List<Project> projects = projectBasicCrudDao.findAll();
        ITable expectedData = new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader()
                .getResourceAsStream("app/dao/impl/projectDataSet/inputDb.xml")).getTable(table);
        ITable actualData = connection.createDataSet().getTable(table);
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getRowCount(), projects.size());
    }

    @Test
    public void findByIdTest() throws SQLException, DatabaseUnitException {
        Project project = projectBasicCrudDao.findById(1);

        ITable expectedData = new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader()
                .getResourceAsStream("app/dao/impl/projectDataSet/inputDb.xml")).getTable(table);
        ITable actualData = connection.createDataSet().getTable(table);
        String expectedTitleOfProject = (String) expectedData.getValue(0, "name");

        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedTitleOfProject, project.getName());
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-mm-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Test
    public void createTest() throws SQLException, DatabaseUnitException {
        Date startDate = parseDate("2019-05-01");
        Date endDate = parseDate("2020-05-01");
        projectBasicCrudDao.create(new Project(5, 55, "project_5", "logo5", startDate, endDate, (long)50, "005", "yellow", "description5"));
        ITable actualData = connection.createDataSet().getTable(table);
        ITable expectedData = new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader()
                .getResourceAsStream("app/dao/impl/projectDataSet/createDb.xml")).getTable(table);
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, columnsToIgnore);
    }

    @Test
    public void deleteByIdTest() throws SQLException, DatabaseUnitException {
        projectBasicCrudDao.deleteById(2);
        ITable actualData = connection.createDataSet().getTable(table);
        ITable expectedData = new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader()
                .getResourceAsStream("app/dao/impl/projectDataSet/deleteDb.xml")).getTable(table);
        Assertion.assertEquals(expectedData, actualData);
    }

    @Test
    public void updateTest() throws SQLException, DatabaseUnitException {
        Date startDate = parseDate("2019-01-19");
        Date endDate = parseDate("2020-01-19");
        projectBasicCrudDao.update(new Project(2, 22, "project_2", "logo2", startDate, endDate, (long)20, "002", "grey", "description2"));
        ITable actualData = connection.createDataSet().getTable(table);
        ITable expectedData = new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader()
                .getResourceAsStream("app/dao/impl/projectDataSet/updateDb.xml")).getTable(table);
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, columnsToIgnore);
    }
}
