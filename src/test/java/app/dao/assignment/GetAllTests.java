package app.dao.assignment;

import app.entities.Assignment;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GetAllTests extends AssignmentDaoTestsInitiator {

    private static final int NUMBER_OF_FIRST_ROW = 0;

    @Test
    public void getAll() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("initial-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(assignmentTable);
        List<Assignment> assignments = assignmentDao.getAll();
        int index = NUMBER_OF_FIRST_ROW;
        for(Assignment assignment : assignments) {
            Assert.assertEquals(expectedTable.getValue(index, "projectId")
                            .toString(),
                    String.valueOf(assignment.getProjectId()));
            Assert.assertEquals(expectedTable.getValue(index, "employeeId")
                            .toString(),
                    String.valueOf(assignment.getEmployeeId()));
            Assert.assertEquals(expectedTable.getValue(index, "workLoadInMinutes")
                            .toString(),
                    String.valueOf(assignment.getWorkLoadInMinutes()));
            index++;
        }
    }
}
