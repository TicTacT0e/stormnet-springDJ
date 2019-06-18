package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.User;
import app.exceptions.EntityNotFoundException;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class UserDaoImplTest extends ConnectionForTests {

    @Autowired
    private BasicCrudDao<User> userDao;

    private static final String USER_TABLE = "Users";

    private static final int NUMBER_OF_FIRST_ROW = 0;

    public UserDaoImplTest() {
        super("app/dao/impl/UserDataSet/initial-dataset.xml");
    }

    @Test
    public void setUpDatabaseTest() throws Exception {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("app/dao/impl/UserDataSet/initial-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(USER_TABLE);

        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable(USER_TABLE);

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void deleteById() throws Exception {
        userDao.deleteById(2);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("app/dao/impl/UserDataSet/delete-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(USER_TABLE);
        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable(USER_TABLE);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteWithNonExistsPrimaryKey() {
        userDao.deleteById(100);
    }

    @Test
    public void edit() throws Exception {
        User user = new User();
        userDao.update(user);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("app/dao/impl/UserDataSet/edit-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(USER_TABLE);
        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable(USER_TABLE);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void editWithNonExistsPrimaryKey() {
        userDao
                .update(new User(5, "Valia", "6754324567", "valia@gmail.by", "ValiaPhotoUrl"));
    }

    @Test
    public void findById() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("app/dao/impl/UserDataSet/find-by-id-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(USER_TABLE);

        User employee = userDao.findById(4);

        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "id")
                        .toString(),
                String.valueOf(employee.getId()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "name")
                        .toString(),
                String.valueOf(employee.getName()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "phone")
                        .toString(),
                String.valueOf(employee.getPhone()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "email")
                        .toString(),
                String.valueOf(employee.getEmail()));
        Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "photoUrl")
                        .toString(),
                String.valueOf(employee.getPhotoUrl()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdWithNonExistsPrimaryKey() {
        userDao.findById(100);
    }

    @Test
    public void getAll() throws DataSetException {
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("app/dao/impl/UserDataSet/initial-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(USER_TABLE);
        List<User> employees = userDao.findAll();
        int index = NUMBER_OF_FIRST_ROW;
        for (User employee : employees) {
            Assert.assertEquals(expectedTable.getValue(index, "id")
                            .toString(),
                    String.valueOf(employee.getId()));
            Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "phone")
                            .toString(),
                    String.valueOf(employee.getPhone()));
            Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "email")
                            .toString(),
                    String.valueOf(employee.getEmail()));
            Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "photoUrl")
                            .toString(),
                    String.valueOf(employee.getPhotoUrl()));
            index++;
        }
    }

    @Test
    public void save() throws Exception {
        User user =
                new User(3, "Valia", "876543567", "valia@gmail.by", "valiaPhotoUrl");

        userDao.create(user);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass()
                        .getClassLoader()
                        .getResourceAsStream("app/dao/impl/UserDataSet/save-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable(USER_TABLE);
        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable(USER_TABLE);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveAlreadyExistsEntity() {
        userDao
                .create(new User(2, "Sergey", "09876123", "sergey@mail.ru", "SergeyPhotoUrl"));
    }
}
