package app.dao;

import app.entities.Invitation;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

public class GetFinfByIdInvitationDaoTest extends InitilizationInvitationDaoTest {

    private static final int NUMBER_OF_FIRST_ROW = 0;

    @Test
    public void getFindById() {
        try {
            IDataSet iDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                    .getResourceAsStream("datasets/Invitation/getFindById-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);

            Invitation invitation = invitationDao.findById(1);

            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "id").toString(),
                    String.valueOf(invitation.getId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "partnerId").toString(),
                    String.valueOf(invitation.getPartnerId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "invitationsCode").toString(),
                    String.valueOf(invitation.getInvitationsCode()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "dateEnd").toString(),
                    String.valueOf(invitation.getDateEnd()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "status").toString(),
                    String.valueOf(invitation.getStatus()));
        } catch (DataSetException e) {
            e.printStackTrace();
        }
    }

//    @Test(expected = EntityNotFoundException.class)
//    public void getFindByIdException() {
//        invitationDao.findById(6);
//    }
}
