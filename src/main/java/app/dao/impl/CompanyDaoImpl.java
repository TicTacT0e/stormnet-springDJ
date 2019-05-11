package app.dao.impl;

import app.dao.CompanyDao;
import app.entities.Company;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Company findById(int id) {
        Company company = sessionFactory.getCurrentSession().get(Company.class, id);
        if (company == null) {
            throw new EntityNotFoundException();
        }
        return company;
    }

    @Override
    public List<Company> findAll() {
        Query query8
                = sessionFactory.getCurrentSession()
                .createQuery("from Company");
        return query8.getResultList();
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession()
                .delete(findById(id));
    }

    @Override
    public void save(Company entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void delete(Company entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public void edit(Company entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }

    //@Autowired
    //JDBCConnection jdbcConnection;

    /*@Override
    public void create(Company company) {
        try(Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into company values (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, company.getId());
            preparedStatement.setString(2, company.getName());
            preparedStatement.setString(3, company.getLogoUrl());
            preparedStatement.setInt(4, company.getOwnerId());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void delete(Company company) {
        deleteById(company.getId());
    }

    @Override
    public void update(Company company) {
        try(Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update company set name = ?, logo = ?," +
                            " ownerId = ? where id = ? ")) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getLogoUrl());
            preparedStatement.setInt(3, company.getOwnerId());
            preparedStatement.setInt(4, company.getId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.getMessage();
        }
    }*/
}

