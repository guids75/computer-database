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
  public List<Company> list(int nbCompanies, int offset, boolean write) {
    jdbcConnection.openConnection();
    request = "SELECT * FROM company";
    if (nbCompanies == -1) {
      request +=" LIMIT ? OFFSET ?";
    }
    List<Company> listCompanies = new ArrayList<>();
    try {
      preparedStatement = jdbcConnection.getConnection().prepareStatement(request);
      if (nbCompanies == -1) {
        preparedStatement.setInt(1, nbCompanies);
        preparedStatement.setInt(1, offset);
      }
      results = preparedStatement.executeQuery(request);
      listCompanies =  resultToObjectMapper.convertToCompanies(preparedStatement.executeQuery(), write);
      preparedStatement.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {
      jdbcConnection.closeConnection();
    }
    return listCompanies;
  }

}
