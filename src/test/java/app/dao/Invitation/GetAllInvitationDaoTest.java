package app.dao.Invitation;

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
                    getResourceAsStream("/datasets/Invitation/getAll-dataset.xml"));
            ITable iTable = iDataSet.getTable(table);
            List<Invitation> invitationList = invitationDao.getAll();
            int index = NUMBER_OF_FIRST_ROW;
            for (Invitation invitation : invitationList) {
                Assert.assertEquals(iTable.getValue(index, "employeeId").toString(),
                        String.valueOf(invitation.getEmployeeId()));
                Assert.assertEquals(iTable.getValue(index, "companyId").toString(),
                        String.valueOf(invitation.getCompanyId()));
                Assert.assertEquals(iTable.getValue(index, "email").toString(),
                        String.valueOf(invitation.getEmail()));
                Assert.assertEquals(iTable.getValue(index, "invitationsCode").toString(),
                        String.valueOf(invitation.getInvitationsCode()));
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
