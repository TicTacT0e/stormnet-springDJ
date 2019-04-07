package app.dao.Invitation;

import app.entities.Invitation;
import app.exceptions.EntityNotFoundException;
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

            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "employeeId").toString(),
                    String.valueOf(invitation.getEmployeeId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "companyId").toString(),
                    String.valueOf(invitation.getCompanyId()));
            Assert.assertEquals(iTable.getValue(NUMBER_OF_FIRST_ROW, "email").toString(),
                    String.valueOf(invitation.getEmail()));
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
