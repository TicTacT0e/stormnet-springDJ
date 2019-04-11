import app.dao.ProjectDao;
import app.entities.Project;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProjectDaoTest extends DBUnitConfig {
    @Autowired
    ProjectDao projectDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("app/dao/impl/inputDb.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public ProjectDaoTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception{
        List<Project> projects = projectDao.getAll();
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("app/dao/impl/inputDb.xml"));
        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData,actualData);
        Assert.assertEquals(expectedData.getTable("project").getRowCount(),projects.size());
    }

    @Test
    public void testSave() throws Exception {
        Date s_date = new Date("01/02/19");
        Date e_date = new Date("01/06/19");
        Project project = new Project(1, "project_1", "logo1", s_date, e_date, 1000l, "001", "white", "description1");
        projectDao.save(project);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("app/dao/impl/inputDb.xml"));
        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "project", ignore);
    }

    @Test

    public void testFindById() throws Exception{
        Date s_date = new Date("01/02/19");
        Date e_date = new Date("01/06/19");

        Project project = new Project(1, "project_1", "logo1", s_date, e_date, 1000l, "001", "white", "description1");
        projectDao.findById(1);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("app/dao/impl/inputDb.xml"));
        IDataSet actualData = tester.getConnection().createDataSet();


    }


}
