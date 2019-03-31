package app.dao.assignmentDaoTests;

import app.entities.Assignment;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Assert;
import org.junit.Test;

public class SaveTests extends AssignmentDaoTests {

    @Test
    public void checkSizeAfterSave() throws Exception {
        int initialRowCount = databaseTester
                .getDataSet()
                .getTable(assignmentTable).getRowCount();
        Assignment assignment =
                new Assignment(4, 7, 12000);
        assignmentDao.save(assignment);
        int rowCountAfterSave = assignmentDao.getAll().size();
        initialRowCount++;
        Assert.assertEquals(initialRowCount, rowCountAfterSave);
    }

    @Test
    @ExpectedDatabase("/saveDataSets/saveDataSet.xml")
    public void save() {
        Assignment assignment =
                new Assignment(4, 7, 12000);
        assignmentDao.save(assignment);
        assignmentDao.getAll();
    }
}