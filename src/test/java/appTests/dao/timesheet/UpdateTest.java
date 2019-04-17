package appTests.dao.timesheet;

import app.entities.Timesheet;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.junit.Test;

public class UpdateTest extends TimesheetDaoTestsInitiator {
    @Test
    public void updateTest() throws Exception {
        timesheetDao.update(new Timesheet(3, 8, null, "notOk"));
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("updateDataSets/update-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("timesheet");
        IDataSet actualDataSet = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev").createDataSet();
        ITable actualTable = actualDataSet.getTable("timesheet");
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
