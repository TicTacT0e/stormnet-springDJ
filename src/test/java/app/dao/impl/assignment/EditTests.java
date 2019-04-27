package app.dao.impl.assignment;

import app.entities.Assignment;
import app.exceptions.EntityNotFoundException;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

public class EditTests extends AssignmentDaoTestsInitiator {

    @Test
    public void edit() throws Exception {
        Assignment assignment = new Assignment(0, 1, 2, 6, 15000);
        assignmentDao.update(assignment);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("assignment/editDataSets/edit-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = EntityNotFoundException.class)
    public void editWithNonExistsPrimaryKey() {
        assignmentDao
                .update(new Assignment(15, 4, 7, 1, 15000));
    }
}
