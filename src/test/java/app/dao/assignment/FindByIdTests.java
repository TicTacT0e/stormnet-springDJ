package app.dao.assignment;

import app.entities.Assignment;
import app.exceptions.EntityNotFoundException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

public class FindByIdTests extends AssignmentDaoTestsInitiator {

    private static final int NUMBER_OF_FIRST_ROW = 0;

    @Test
    public void findById() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("findByIdDataSets/find-by-id-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);

        Assignment assignment = assignmentDao.findById(3, 4);

        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "id")
                        .toString(),
                String.valueOf(assignment.getId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "projectId")
                        .toString(),
                String.valueOf(assignment.getProjectId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "employeeId")
                        .toString(),
                String.valueOf(assignment.getEmployeeId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "activityId")
                        .toString(),
                String.valueOf(assignment.getActivityId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "workLoadInMinutes")
                        .toString(),
                String.valueOf(assignment.getWorkLoadInMinutes()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdWithNonExistsPrimaryKey() {
        assignmentDao
                .findById(4, 7);
    }
}
