package app.dao.impl.Invitation;

import app.entities.Invitation;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class UpdateInvitationDaoTest extends InitilizationInvitationDaoTest {

    @Test
    public void updateInvitation() {
        basicCrudDao.update(new Invitation(
                1, 1, "invatationCode",
                new Date(2019, 3, 3), "status"
        ));
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream(""));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualIDataSet = getMySqlConnection().createDataSet();
            ITable actualITable = actualIDataSet.getTable(table);

            Assertion.assertEquals(iTable, actualITable);
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void updateInvitationException() {
        basicCrudDao.update(new Invitation(
                6, 1, "invatationCode",
                new Date(2019, 3, 3), "status"
        ));
    }
}
