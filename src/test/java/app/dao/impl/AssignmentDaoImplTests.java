package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Assignment;
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


public class AssignmentDaoImplTests extends ConnectionForTests {

	private static final String ASSIGNMENT_TABLE = "Assignments";
	private static final int NUMBER_OF_FIRST_ROW = 0;

	@Autowired
	private BasicCrudDao<Assignment> assignmentDao;


	public AssignmentDaoImplTests() {
		super("app/dao/impl/assignmentDatasets/initial-dataset.xml");
	}

	@Test
	public void deleteById() throws Exception {
		assignmentDao.deleteById(5);
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(getClass()
						.getClassLoader()
						.getResourceAsStream("app/dao/impl/assignmentDatasets/delete-dataset.xml"));
		ITable expectedTable = expectedDataSet.getTable(ASSIGNMENT_TABLE);
		IDataSet actualDataSet = connection.createDataSet();
		ITable actualTable = actualDataSet.getTable(ASSIGNMENT_TABLE);
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteWithNonExistsPrimaryKey() {
		assignmentDao.deleteById(100);
	}

	@Test
	public void edit() throws Exception {
		Assignment assignment = new Assignment(5, 5, 1, 1, 10000);
		assignmentDao.update(assignment);
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(getClass()
						.getClassLoader()
						.getResourceAsStream("app/dao/impl/assignmentDatasets/edit-dataset.xml"));
		ITable expectedTable = expectedDataSet.getTable(ASSIGNMENT_TABLE);
		IDataSet actualDataSet = connection.createDataSet();
		ITable actualTable = actualDataSet.getTable(ASSIGNMENT_TABLE);
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void editWithNonExistsPrimaryKey() {
		assignmentDao
				.update(new Assignment(5, 100, 100, 100, 15000));
	}

	@Test
	public void findById() throws DataSetException {
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(getClass()
						.getClassLoader()
						.getResourceAsStream("app/dao/impl/assignmentDatasets/find-by-id-dataset.xml"));
		ITable expectedTable = expectedDataSet.getTable(ASSIGNMENT_TABLE);

		Assignment assignment = assignmentDao.findById(4);

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
		Assert.assertEquals(expectedTable.getValue(NUMBER_OF_FIRST_ROW, "workLoad")
						.toString(),
				String.valueOf(assignment.getWorkLoad()));
	}

	@Test(expected = EntityNotFoundException.class)
	public void findByIdWithNonExistsPrimaryKey() {
		assignmentDao.findById(100);
	}

	@Test
	public void getAll() throws DataSetException {
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(getClass()
						.getClassLoader()
						.getResourceAsStream("app/dao/impl/assignmentDatasets/initial-dataset.xml"));
		ITable expectedTable = expectedDataSet.getTable(ASSIGNMENT_TABLE);
		List<Assignment> assignments = assignmentDao.findAll();
		int index = NUMBER_OF_FIRST_ROW;
		for (Assignment assignment : assignments) {
			Assert.assertEquals(expectedTable.getValue(index, "id")
							.toString(),
					String.valueOf(assignment.getId()));
			Assert.assertEquals(expectedTable.getValue(index, "projectId")
							.toString(),
					String.valueOf(assignment.getProjectId()));
			Assert.assertEquals(expectedTable.getValue(index, "employeeId")
							.toString(),
					String.valueOf(assignment.getEmployeeId()));
			Assert.assertEquals(expectedTable.getValue(index, "activityId")
							.toString(),
					String.valueOf(assignment.getActivityId()));
			Assert.assertEquals(expectedTable.getValue(index, "workLoad")
							.toString(),
					String.valueOf(assignment.getWorkLoad()));
			index++;
		}
	}

	@Test
	public void save() throws Exception {
		Assignment assignment =
				new Assignment(6, 5, 1, 5, 10000);
		assignmentDao.create(assignment);
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
				.build(getClass()
						.getClassLoader()
						.getResourceAsStream("app/dao/impl/assignmentDatasets/save-dataset.xml"));
		ITable expectedTable = expectedDataSet.getTable(ASSIGNMENT_TABLE);
		IDataSet actualDataSet = connection.createDataSet();
		ITable actualTable = actualDataSet.getTable(ASSIGNMENT_TABLE);
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void saveAlreadyExistsEntity() {
		assignmentDao
				.create(new Assignment(4, 2, 1, 1, 4500));
	}
}
