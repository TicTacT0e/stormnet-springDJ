package app.dao.assignmentDaoTests;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;

public class GetAllTests extends AssignmentDaoTests {

    @Test
    @ExpectedDatabase("initial-dataset.xml")
    public void getAllTest() {
        assignmentDao.getAll();
    }
}
