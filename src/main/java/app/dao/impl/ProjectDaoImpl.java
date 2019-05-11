package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Project;
import app.exceptions.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProjectDaoImpl implements BasicCrudDao<Project> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Project findById(int id) {
        Project project = sessionFactory.openSession().get(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException();
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = (List<Project>) sessionFactory.openSession().createQuery("from Project");
        return projects;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(findById(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void create(Project entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Project entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Project entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}


















/*package app.dao.impl;

import app.dao.ProjectDao;
import app.entities.Project;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Project findById(int id) {
        Project project = sessionFactory.getCurrentSession().get(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException();
        }
        return project;
    }

    @Override
    public List<Project> getAll() {
        Query query9
                = sessionFactory.getCurrentSession()
                .createQuery("from Project");
        return query9.getResultList();
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void save(Project entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Project entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void edit(Project entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }

    private static final String GET_ALL = "SELECT * FROM timesheet_dev.project";
    private static final String FIND_BY_ID = "SELECT * FROM timesheet_dev.project WHERE id=?";
    private static final String SAVE = "INSERT INTO timesheet_dev.project(name, logoUrl, " +
            "startDate, endDate, manHours, code, color, description) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM timesheet_dev.project WHERE id=?";
    private static final String EDIT = "UPDATE timesheet_dev.project SET name=?, logoUrl=?, " +
            "startDate=?, endDate=?, manHours=?, code=?, color=?, description=? WHERE id=?";
    private static final String IS_EXIST = "SELECT EXISTS (SELECT id FROM timesheet_dev.project" +
            "WHERE id=?)";
    private static final int ROW_EXISTS = 1;

    @Autowired
    JDBCConnection jdbcConnection;

    public PreparedStatement createPreparedStatement(Project project, Connection connection, String command) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, project.getName());
        preparedStatement.setString(2, project.getLogoUrl());
        preparedStatement.setDate(3, project.getStartDate());
        preparedStatement.setDate(4, project.getEndDate());
        preparedStatement.setLong(5, project.getManHours());
        preparedStatement.setString(6, project.getCode());
        preparedStatement.setString(7, project.getColor());
        preparedStatement.setString(8, project.getDescription());
        return preparedStatement;
    }

    private List<Project> projectListMapper(ResultSet resultSet) throws SQLException {
        List<Project> projectList = new LinkedList<>();
        Project project;
        while (resultSet.next()) {
            project = projectMapper(resultSet);
            projectList.add(project);
        }
        return projectList;
    }

    private Project projectMapper(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setLogoUrl(resultSet.getString("logoUrl"));
        project.setStartDate(resultSet.getDate("startDate"));
        project.setEndDate(resultSet.getDate("endDate"));
        project.setManHours(resultSet.getLong("manHours"));
        project.setCode(resultSet.getString("code"));
        project.setColor(resultSet.getString("color"));
        project.setDescription(resultSet.getString("description"));
        return project;
    }

    private boolean isProjectExists(int id) {
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == ROW_EXISTS) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}*/






