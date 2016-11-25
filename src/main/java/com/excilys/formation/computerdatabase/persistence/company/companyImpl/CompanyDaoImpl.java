package com.excilys.formation.computerdatabase.persistence.company.companyImpl;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.JdbcConnection;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author GUIDS
 *
 */
public enum CompanyDaoImpl implements CompanyDao {

  INSTANCE;
  private static HikariConnectionPool hikariConnectionPool = 
      HikariConnectionPool.INSTANCE; // get the connection

  // requests
  private static final String LIST_REQUEST = "SELECT * FROM company LIMIT ? OFFSET ?";
  private static final String NUMBER_REQUEST = "SELECT COUNT(*) as number FROM company";
  private static final String COMPANY_REQUEST = "SELECT * FROM company where id=?";

  private ResultSet results;
  private Connection connection = null;

  @Override
  public List<Company> list(Constraints constraints) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setInt(1, constraints.getLimit());
      preparedStatement.setInt(2, constraints.getOffset());
      List<Company> list = ResultMapper.convertToCompanies(preparedStatement.executeQuery());
      connection.commit();
      return list;
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " + sqx.getMessage());
      }
      throw new ConnectionException("companies failed to be listed", exception);
    }
  }

  @Override
  public int count() throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
        Statement statement = connection.createStatement()) {
      connection.setAutoCommit(false);
      results = statement.executeQuery(NUMBER_REQUEST);
      results.next();
      connection.commit();
      return results.getInt("number");
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " + sqx.getMessage());
      }
      throw new ConnectionException("companies failed to be counted", exception);
    }
  }

  @Override
  public Company getCompany(long id) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setLong(1, id);
      results = preparedStatement.executeQuery();
      results.next();
      connection.commit();
      return ResultMapper.convertToCompany(results);
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " + sqx.getMessage());
      }
      throw new ConnectionException("company failed to be get", exception);
    }
  }

}
