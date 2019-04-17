package appTests.dao.timesheet;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.junit.Test;

public class DeleteTest extends TimesheetDaoTestsInitiator {
    @Test
    public void deleteTest() throws Exception {
        timesheetDao.delete(3);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("deleteDataSets/delete-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("timesheet");
        IDataSet actualDataSet = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev").createDataSet();
        ITable actualTable = actualDataSet.getTable("timesheet");
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
