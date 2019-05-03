package app.dao.impl.Invitation;

import app.entities.Invitation;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GetAllInvitationDaoTest extends InitilizationInvitationDaoTest {

    private static final int NUMBER_OF_FIRST_ROW = 0;

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
                Assert.assertEquals(iTable.getValue(index, "invitationsCode").toString(),
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
}
