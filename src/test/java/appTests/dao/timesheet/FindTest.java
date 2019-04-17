package appTests.dao.timesheet;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.junit.Test;

public class FindTest extends TimesheetDaoTestsInitiator {
    @Test
    public void findTest() throws Exception {
        timesheetDao.findById(3);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("findDataSets/find-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("timesheet");
        IDataSet actualDataSet = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev").createDataSet();
        ITable actualTable = actualDataSet.getTable("timesheet");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void findTestNullPointer() throws Exception {
        timesheetDao.findById(11111);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("findDataSets/find-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("timesheet");
        IDataSet actualDataSet = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev").createDataSet();
        ITable actualTable = actualDataSet.getTable("timesheet");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void findAllTest() throws Exception {
        timesheetDao.findAll();
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("findDataSets/find-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("timesheet");
        IDataSet actualDataSet = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev").createDataSet();
        ITable actualTable = actualDataSet.getTable("timesheet");
        Assertion.assertEquals(expectedTable, actualTable);
    }


}
