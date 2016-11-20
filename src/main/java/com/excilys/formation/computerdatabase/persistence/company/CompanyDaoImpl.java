package com.excilys.formation.computerdatabase.persistence.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.JdbcConnection;

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
  private static ResultMapper resultToObjectMapper = ResultMapper.getInstance(); //utility to process resultSets
  private static JdbcConnection jdbcConnection = JdbcConnection.getInstance(); //get the connection

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
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = jdbcConnection.getConnection().prepareStatement(LIST_REQUEST)) {
      preparedStatement.setInt(1, nbCompanies);
      preparedStatement.setInt(2, offset);
      results = preparedStatement.executeQuery();
      return resultToObjectMapper.convertToCompanies(preparedStatement.executeQuery());
    } catch (SQLException exception) {
      throw new ConnectionException("companies failed to be listed", exception);
    }
  }

  @Override
  public int count() throws ConnectionException {
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        Statement statement = jdbcConnection.getConnection().createStatement()) { 
      results = statement.executeQuery(NUMBER_REQUEST); 
      results.next();
      return results.getInt("number");
    } catch (SQLException exception) { 
      throw new ConnectionException("companies failed to be counted", exception);
    } 
  } 

  @Override
  public Company getCompany(int id) throws ConnectionException {
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_REQUEST)) {
      preparedStatement.setInt(1,id);
      results = preparedStatement.executeQuery();
      results.next();
      return resultToObjectMapper.convertToCompany(results);
    } catch (SQLException exception) {
      throw new ConnectionException("company failed to be get", exception);
    }
  }

}
