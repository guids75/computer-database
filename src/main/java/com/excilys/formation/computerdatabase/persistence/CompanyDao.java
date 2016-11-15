package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.mapper.ResultToObjectMapper;
import com.excilys.formation.computerdatabase.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements CompanyDaoInterface<Company> {

  private static CompanyDao companyDAO = new CompanyDao();
  private static ResultToObjectMapper resultToObjectMapper = ResultToObjectMapper.getInstance();
  private static final JdbcConnection jdbcConnection = JdbcConnection.getInstance();

  private static ResultSet results;
  private static PreparedStatement preparedStatement;
  private static String request;
  //private Page page;
  
  private CompanyDao() {
  }
    
  public static CompanyDao getInstance() {
    return companyDAO;
  }

  /** Get a list of all the companies.
   * 
   * @param nbCompanies : number of companies to display
   * @param offset : the offset used to display
   */
  public List<Company> list(int nbCompanies, int offset) {
    jdbcConnection.openConnection();
    request = "SELECT * FROM company LIMIT ? OFFSET ?";
    List<Company> listCompanies = new ArrayList<>();
    try {
      preparedStatement = jdbcConnection.getConnection().prepareStatement(request);
      preparedStatement.setInt(1, nbCompanies);
      preparedStatement.setInt(2, offset);
      results = preparedStatement.executeQuery(request);
      listCompanies =  resultToObjectMapper.convertToCompanies(preparedStatement.executeQuery());
      preparedStatement.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {
      jdbcConnection.closeConnection();
    }
    return listCompanies;
  }
  
  public int getNumber(){
	  int numberCompanies = -1; 
	  request = "SELECT COUNT(*) as number FROM company";
	  try { 
	    Statement statement = jdbcConnection.getConnection().createStatement();
	    results = statement.executeQuery(request); 
	    results.next();
	    numberCompanies = results.getInt("number");
	  } catch (SQLException e) { 
	    e.printStackTrace(); 
	  } 
	  return numberCompanies; 
  } 

  public Company getCompany(int id){
    request = "SELECT * FROM company where id=?";
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
      preparedStatement.setInt(1,id);
      results = preparedStatement.executeQuery();
      results.next();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultToObjectMapper.convertToCompany(results);
  }
  
}
