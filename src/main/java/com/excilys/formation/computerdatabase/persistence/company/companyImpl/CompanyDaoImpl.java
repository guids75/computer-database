package com.excilys.formation.computerdatabase.persistence.company.companyImpl;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.EJBException;

/**
 * @author GUIDS
 *
 */
public enum CompanyDaoImpl implements CompanyDao {

    INSTANCE;

    // requests
    private static final String LIST_REQUEST = "SELECT company.id as companyId, company.name as companyName FROM company LIMIT ? OFFSET ?";
    private static final String DELETE_REQUEST = "DELETE FROM company WHERE id=?";
    private static final String NUMBER_REQUEST = "SELECT COUNT(company.id) as number FROM company";
    private static final String COMPANY_REQUEST = "SELECT company.id as companyId, company.name as companyName FROM company where id=?";
    
    private static HikariConnectionPool hikariConnectionPool = 
            HikariConnectionPool.INSTANCE; // get the connection
    private ResultSet results;

    @Override
    public List<Company> list(Constraints constraints) throws ConnectionException {
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
            preparedStatement.setInt(1, constraints.getLimit());
            preparedStatement.setInt(2, constraints.getOffset());
            List<Company> list = ResultMapper.convertToCompanies(preparedStatement.executeQuery());
            hikariConnectionPool.closeConnection(connection);
            return list;
        } catch (SQLException exception) {
            throw new ConnectionException("companies failed to be listed", exception);
        }
    }

    @Override
    public void delete(Constraints constraints, Connection connection) throws ConnectionException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST)) {
            preparedStatement.setLong(1, constraints.getIdCompany());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ConnectionException("company failed to be deleted", exception);
        }
    }

    @Override
    public int count() throws ConnectionException {
        try (Connection connection = hikariConnectionPool.getConnection();
                Statement statement = connection.createStatement()) {
            results = statement.executeQuery(NUMBER_REQUEST);
            results.next();
            hikariConnectionPool.closeConnection(connection);
            return results.getInt("number");
        } catch (SQLException exception) {
            throw new ConnectionException("companies failed to be counted", exception);
        }
    }

    @Override
    public Company getCompany(Long id) throws ConnectionException {
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_REQUEST)) {
            preparedStatement.setLong(1, id);
            results = preparedStatement.executeQuery();
            results.next();
            hikariConnectionPool.closeConnection(connection);
            return ResultMapper.convertToCompany(results);
        } catch (SQLException exception) {
            throw new ConnectionException("company failed to be get", exception);
        }
    }

}
