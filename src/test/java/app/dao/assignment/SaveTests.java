package app.dao.assignment;

import app.entities.Assignment;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

public class SaveTests extends AssignmentDaoTestsInitiator {

    @Test
    public void save() throws Exception {
        Assignment assignment =
                new Assignment(4, 4, 7, 5, 12000);
        assignmentDao.save(assignment);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("saveDataSets/save-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        IDataSet actualDataSet = getMySqlConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable(assignmentTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
