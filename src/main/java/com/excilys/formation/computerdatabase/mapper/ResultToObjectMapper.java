package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.model.Company;
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
public class ResultToObjectMapper {

  private static LocalDateMapper localMapper = LocalDateMapper.getInstance(); //utility to process dates
  private static ResultToObjectMapper resultToObjectMapper = new ResultToObjectMapper(); //singleton of this class
  private static final Logger slf4jLogger = LoggerFactory.getLogger(ResultToObjectMapper.class);


  /**
   * Private constructor for a singleton.
   */
  private ResultToObjectMapper() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static ResultToObjectMapper getInstance() {
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
      Computer computer = new Computer.ComputerBuilder(results.getInt("comput.Id"), 
          results.getString("comput.Name"), new Company(results.getInt("compan.Id"), results.getString("compan.Name")))
          .introduced(localMapper.convertToLocalDate(results.getDate("comput.Introduced")))
          .discontinued(localMapper.convertToLocalDate(results.getDate("comput.Discontinued")))
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
        System.out.println(1);
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
      Company company = new Company();
      company.setId(results.getInt("Id"));
      company.setName(results.getString("Name"));
    } catch (SQLException exception) {
      slf4jLogger.error("Error in ResultToObject in convertToCompany");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

}
