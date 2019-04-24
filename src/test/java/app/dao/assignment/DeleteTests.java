package app.dao.assignment;

import app.exceptions.EntityNotFoundException;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

public class DeleteTests extends AssignmentDaoTestsInitiator {

    @Test
    public void deleteIdInParameters() throws Exception {
        assignmentDao.deleteById(2);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("deleteDataSets/delete-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteWithNonExistsPrimaryKey() {
        assignmentDao.deleteById(4);
    }
}
