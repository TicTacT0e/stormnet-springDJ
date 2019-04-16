package app.dao.impl;

import app.dao.BasicCrudDao;
import app.entities.Company;
import app.services.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CompanyDaoImpl implements BasicCrudDao<Company> {

    @Autowired
    JDBCConnection jdbcConnection;

    private Company getCompany(ResultSet resultSet) throws Exception {
        Company company = null;
        try{
            company = new Company(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("logo"),
                    resultSet.getInt("ownerId"));
        }
        catch (SQLException e){
            e.getMessage();
        }
        return company;
    }

    @Override
    public synchronized List<Company> findAll(){

        List<Company> allCompanys = new LinkedList<>();

        try(Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Company")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                allCompanys.add(getCompany(resultSet));
            }

        } catch (Exception e){
            e.getMessage();
        }
        return allCompanys;
    }



    @Override
    public synchronized Company findById(int id) {
        Company company = null;
        try(Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from company where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            company = getCompany(resultSet);
        } catch (Exception e){
            e.getMessage();
        }
        return company;
    }

    @Override
    public synchronized void deleteById(int id) {
        try(Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from company where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
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
    }
}

