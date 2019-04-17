package appTests.dao.timesheet;

import app.config.beans.DaoConfig;
import app.config.beans.PropertyConfig;
import app.dao.TimesheetDao;
import app.services.JDBCConnection;
import org.dbunit.DBTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class, PropertyConfig.class})
public class TimesheetDaoTestsInitiator extends DBTestCase {

    @Autowired
    protected TimesheetDao timesheetDao;
    @Autowired
    protected JDBCConnection jdbcConnection;

    @Before
    public void setUp() throws Exception {
        IDataSet data = getDataSet();
        IDatabaseConnection connection = new MySqlConnection(jdbcConnection.getConnection(), "timesheet_dev");
        DatabaseOperation.CLEAN_INSERT.execute(connection, data);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("initial-dataset.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
}
