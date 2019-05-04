package app.dao.impl.Invitation;

import app.dao.impl.Invitation.InitilizationInvitationDaoTest;
import app.entities.Invitation;
import app.exceptions.EntityNotFoundException;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class InvitationsDaoImplTest extends InitilizationInvitationDaoTest {

    private static final int NUMBER_OF_FIRST_ROW = 0;

    @Test
    public void deleteById() {
        basicCrudDao.deleteById(4);
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream("datasets/Invitation/delete-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualDataSet = getMySqlConnection().createDataSet();
            ITable actualITable = actualDataSet.getTable(table);
            Assertion.assertEquals(iTable, actualITable);
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteByObject() {
        basicCrudDao.delete(new Invitation(
                1, 1,
                "invatationCode", new Date(2019 ,03,03), "status"
        ));

        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream(""));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualDataSet = getMySqlConnection().createDataSet();
            ITable actualITable = actualDataSet.getTable(table);
            Assertion.assertEquals(iTable, actualITable);
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteInvitationException() {
        basicCrudDao.deleteById(34);
    }

    @Test
    public void getAllInvitation() {
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader().
                    getResourceAsStream("datasets/Invitation/getAll-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);
            List<Invitation> invitationList = basicCrudDao.findAll();
            int index = NUMBER_OF_FIRST_ROW;
            for (Invitation invitation : invitationList) {
                Assert.assertEquals(iTable.getValue(index, "id").toString(),
                        String.valueOf(invitation.getId()));
                Assert.assertEquals(iTable.getValue(index, "partnerId").toString(),
                        String.valueOf(invitation.getPartnerId()));
                Assert.assertEquals(iTable.getValue(index, "code").toString(),
                        String.valueOf(invitation.getCode()));
                Assert.assertEquals(iTable.getValue(index, "dateEnd").toString(),
                        String.valueOf(invitation.getDateEnd()));
                Assert.assertEquals(iTable.getValue(index, "status").toString(),
                        String.valueOf(invitation.getStatus()));
                index++;
            }
        } catch (DataSetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFindById() {
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream("datasets/Invitation/getFindById-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            Invitation invitation = basicCrudDao.findById(1);

            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "id").toString(),
                    String.valueOf(invitation.getId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "partnerId").toString(),
                    String.valueOf(invitation.getPartnerId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "code").toString(),
                    String.valueOf(invitation.getCode()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "dateEnd").toString(),
                    String.valueOf(invitation.getDateEnd()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "status").toString(),
                    String.valueOf(invitation.getStatus()));
        } catch (DataSetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveInvitation() {
        basicCrudDao.create(new Invitation(
                5, 1,"invatationCode", new Date(2019,3,3), "status"
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
