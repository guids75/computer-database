package com.excilys.formation.computerdatabase.persistence.company.companyImpl;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.JdbcConnection;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author GUIDS
 *
 */
public class CompanyDaoImpl implements CompanyDao {

  private static CompanyDaoImpl companyDAO = new CompanyDaoImpl(); ////singleton of this class
  private static HikariConnectionPool hikariConnectionPool = HikariConnectionPool.getInstance(); //get the connection

  //requests
  private static final String LIST_REQUEST = "SELECT * FROM company LIMIT ? OFFSET ?";
  private static final String NUMBER_REQUEST = "SELECT COUNT(*) as number FROM company";
  private static final String COMPANY_REQUEST = "SELECT * FROM company where id=?";

  private ResultSet results;

  /**
   * Private constructor for a singleton.
   */
  private CompanyDaoImpl() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static CompanyDaoImpl getInstance() {
    return companyDAO;
  }

  @Override
  public List<Company> list(int nbCompanies, int offset) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
      preparedStatement.setInt(1, nbCompanies);
      preparedStatement.setInt(2, offset);
      results = preparedStatement.executeQuery();
      return ResultMapper.convertToCompanies(preparedStatement.executeQuery());
    } catch (SQLException exception) {
      throw new ConnectionException("companies failed to be listed", exception);
    }
  }

  @Override
  public int count() throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        Statement statement = connection.createStatement()) { 
      results = statement.executeQuery(NUMBER_REQUEST); 
      results.next();
      return results.getInt("number");
    } catch (SQLException exception) { 
      throw new ConnectionException("companies failed to be counted", exception);
    } 
  } 

  @Override
  public Company getCompany(int id) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_REQUEST)) {
      preparedStatement.setInt(1,id);
      results = preparedStatement.executeQuery();
      results.next();
      return ResultMapper.convertToCompany(results);
    } catch (SQLException exception) {
      throw new ConnectionException("company failed to be get", exception);
    }
  }

}
