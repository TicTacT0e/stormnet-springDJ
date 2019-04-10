package app.dao;

import app.entities.Invitation;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class SaveInvitationDaoTest extends InitilizationInvitationDaoTest {

    @Test
    public void saveInvitation() {
        invitationDao.save(new Invitation(
                5, 1, "email@gmail.com",
                "invatationCode", new Date(2019,3,3), "status"
        ));
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream("datasets/Invitation/save-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualDataSet = getMySqlConnection().createDataSet();
            ITable actualITAble = actualDataSet.getTable(table);

            Assertion.assertEquals(iTable, actualITAble);
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
