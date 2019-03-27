package app.dao.assignmentDaoTests;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;

public class FindByIdTests extends AssignmentDaoTests {

    @Test
    @ExpectedDatabase("findByIdDataSets/findByIdDataSet.xml")
    public void findById() {
        assignmentDao.findById(3, 4);
    }

    @Test(expected = NullPointerException.class)
    public void findByIdWithNonExistsPrimaryKey() {
        assignmentDao.findById(4, 7);
    }
}
