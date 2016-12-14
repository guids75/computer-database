package com.excilys.formation.computerdatabase.persistence.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * @author GUIDS
 *
 */
public class CompanyDaoImpl implements CompanyDao {

    // requests
    private static final String LIST_REQUEST = "SELECT company.id as companyId, company.name as companyName FROM company LIMIT ? OFFSET ?";
    private static final String DELETE_REQUEST = "DELETE FROM company WHERE id=?";
    private static final String NUMBER_REQUEST = "SELECT COUNT(company.id) as number FROM company";
    private static final String COMPANY_REQUEST = "SELECT company.id as companyId, company.name as companyName FROM company where id=?";
 
    private DataSource dataSource; // get the connection
    private ResultSet results;
    
    public CompanyDaoImpl() {
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Company> list(Constraints constraints) throws ConnectionException {
        if (constraints == null || (constraints.getLimit() == -1 || constraints.getOffset() == -1)) {
            throw new IllegalArgumentException("A limit or an offset is missing in the constraints");
        }
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
            preparedStatement.setInt(1, constraints.getLimit());
            preparedStatement.setInt(2, constraints.getOffset());
            results = preparedStatement.executeQuery();
            List<Company> list = ResultMapper.convertToCompanies(preparedStatement.executeQuery());
            return list;
        } catch (SQLException exception) {
            throw new ConnectionException("companies failed to be listed", exception);
        }
    }

    @Override
    public void delete(Constraints constraints, Connection connection) throws ConnectionException {
        if (constraints == null || (constraints.getIdCompany() == -1L || connection == null)) {
            throw new IllegalArgumentException("A company is missing in the constraints or the connection is closed");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST)) {
            preparedStatement.setLong(1, constraints.getIdCompany());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ConnectionException("company failed to be deleted", exception);
        }
    }

    @Override
    public int count() throws ConnectionException {
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            results = statement.executeQuery(NUMBER_REQUEST);
            results.next();
            return results.getInt("number");
        } catch (SQLException exception) {
            throw new ConnectionException("companies failed to be counted", exception);
        }
    }

    @Override
    public Company getCompany(Long id) throws ConnectionException {
        if (id < 1) {
            throw new IllegalArgumentException("The id given for the company is wrong");
        }
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_REQUEST)) {
            preparedStatement.setLong(1, id);
            results = preparedStatement.executeQuery();
            results.next();
            return ResultMapper.convertToCompany(results);
        } catch (SQLException exception) {
            throw new ConnectionException("company failed to be get", exception);
        }
    }

}
