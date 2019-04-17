package appTests.dao.timesheet;

import app.entities.Timesheet;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.junit.Test;

public class SaveTest extends TimesheetDaoTestsInitiator {
    @Test
    public void saveTest() throws Exception {
        timesheetDao.add(new Timesheet(5, 5, null, "ok"));
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("saveDataSets/save-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("timesheet");
        IDataSet actualDataSet = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev").createDataSet();
        ITable actualTable = actualDataSet.getTable("timesheet");
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
