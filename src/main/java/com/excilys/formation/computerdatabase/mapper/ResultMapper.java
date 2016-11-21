package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public class ResultMapper {

  private static LocalDateMapper localMapper = LocalDateMapper.getInstance(); //utility to process dates
  private static ResultMapper resultToObjectMapper = new ResultMapper(); //singleton of this class
  private static final Logger slf4jLogger = LoggerFactory.getLogger(ResultMapper.class);


  /**
   * Private constructor for a singleton.
   */
  private ResultMapper() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static ResultMapper getInstance() {
    return resultToObjectMapper;
  }

  /** Convert a resultSet to the list of computers inside.
   * 
   * @param results : the result of a query
   * @return the list of computers corresponding
   */
  public List<Computer> convertToComputers(ResultSet results) {
    List<Computer> computers = new ArrayList<>();
    try {
      while (results.next()) {
        computers.add(convertToComputer(results));
      }
    } catch (SQLException exception) {
      slf4jLogger.error("Error in ResultToObject in convertToComputers");
      slf4jLogger.error(exception.getMessage());
    }
    return computers;
  }

  /** Convert the first element of a resultSet to the computer inside.
   * 
   * @param results : the result of a query
   * @return the computer corresponding
   */
  public Computer convertToComputer(ResultSet results) {
    try {
      Computer computer = new Computer.ComputerBuilder(results.getString("comput.Name"))
          .id(results.getInt("comput.Id"))
          .introduced(localMapper.convertToLocalDate(results.getDate("comput.Introduced")))
          .discontinued(localMapper.convertToLocalDate(results.getDate("comput.Discontinued")))
          .company(new Company.CompanyBuilder(results.getString("compan.Name")).build())
          .build();
      return computer;
    } catch (SQLException exception) {
      slf4jLogger.error("Error in ResultToObject in convertToComputer");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  /** Convert a resultSet to the list of companies inside.
   * 
   * @param results : the result of a query
   * @return the list of computers corresponding
   */
  public List<Company> convertToCompanies(ResultSet results) {
    List<Company> companies = new ArrayList<>();
    try {
      while (results.next()) {
        companies.add(convertToCompany(results));
      }
    } catch (SQLException exception) {
      slf4jLogger.error("Error in ResultToObject in convertToCompanies");
      slf4jLogger.error(exception.getMessage());
    }
    return companies;
  }

  /** Convert the first element of a resultSet to the company inside.
   * 
   * @param results : the result of a query
   * @return the company corresponding
   */
  public Company convertToCompany(ResultSet results) {
    try {
      CompanyBuilder company = new Company.CompanyBuilder(results.getString("Name"));
      company.id(results.getInt("Id"));
      return company.build();
    } catch (SQLException exception) {
      slf4jLogger.error("Error in ResultToObject in convertToCompany");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

}
