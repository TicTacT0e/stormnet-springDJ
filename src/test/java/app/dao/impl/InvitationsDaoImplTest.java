package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Invitation;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;


public class InvitationsDaoImplTest extends ConnectionForTests {

    @Autowired
    protected BasicCrudDao<Invitation> basicCrudDao;

    private static final int NUMBER_OF_FIRST_ROW = 0;

    protected final String table = "Invitations";

    public InvitationsDaoImplTest() {
        super("app/dao/impl/invitationDataSet/initilization-dataset.xml");
    }

    @Test
    public void setUpDatabaseTest() throws Exception {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader()
                .getResourceAsStream("app/dao/impl/invitationDataSet/initilization-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(table);
        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable(table);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void deleteById() {
        basicCrudDao.deleteById(4);
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream("app/dao/impl/invitationDataSet/delete-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualDataSet = connection.createDataSet();
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
                "invatationCode", new Date(2019, 03, 03), "status"
        ));

        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream(""));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualDataSet = connection.createDataSet();
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

    @Test(expected = Exception.class)
    public void deleteInvitationException() {
        basicCrudDao.deleteById(8);
    }

    @Test
    public void getAllInvitation() {
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader().
                    getResourceAsStream("app/dao/impl/invitationDataSet/getAll-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);
            List<Invitation> invitationList = basicCrudDao.findAll();
            int index = NUMBER_OF_FIRST_ROW;
            for (Invitation invitation : invitationList) {
                Assert.assertEquals(iTable.getValue(index, "id").toString(),
                        String.valueOf(invitation.getId()));
                Assert.assertEquals(iTable.getValue(index, "partnerId").toString(),
                        String.valueOf(invitation.getEmployeeId()));
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
                    .getResourceAsStream("app/dao/impl/invitationDataSet/getFindById-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            Invitation invitation = basicCrudDao.findById(1);

            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "id").toString(),
                    String.valueOf(invitation.getId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "partnerId").toString(),
                    String.valueOf(invitation.getEmployeeId()));
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
                5, 1, "invatationCode", new Date(2019, 3, 3), "status"
        ));
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream("app/dao/impl/invitationDataSet/save-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            IDataSet actualDataSet = connection.createDataSet();
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

            IDataSet actualIDataSet = connection.createDataSet();
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
