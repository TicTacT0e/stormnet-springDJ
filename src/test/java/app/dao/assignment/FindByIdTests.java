package app.dao.assignment;

import app.entities.Assignment;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class FindByIdTests extends AssignmentDaoTestsInitiator {

    @Test
    public void findById() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("findByIdDataSets/find-by-id-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);

        Assignment assignment = assignmentDao.findById(3, 4);

        Assert.assertEquals(expectedTable.getValue(0, "projectId"),
                assignment.getProjectId());

        //Assertion.assertEquals(expectedTable, assignment);
    }

    @Test(expected = NullPointerException.class)
    public void findByIdWithNonExistsPrimaryKey() {
        assignmentDao.findById(4, 7);
    }
}
