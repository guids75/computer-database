package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GUIDS
 *
 */
public class ResultToObjectMapper {

  private static LocalDateMapper localMapper = LocalDateMapper.getInstance(); //utility to process dates
  private static ResultToObjectMapper resultToObjectMapper = new ResultToObjectMapper(); //singleton of this class

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
      while (!results.isLast()) {
        computers.add(convertToComputer(results));
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
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
      results.next();
      Computer computer = new Computer.ComputerBuilder(results.getInt("comput.Id"), 
          results.getString("comput.Name"), new Company(results.getInt("compan.Id"), results.getString("compan.Name")))
          .introduced(localMapper.convertToLocalDate(results.getDate("comput.Introduced")))
          .discontinued(localMapper.convertToLocalDate(results.getDate("comput.Discontinued")))
          .build();
      return computer;
    } catch (SQLException exception) {
      exception.printStackTrace();
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
      while (!results.isLast()) {
        companies.add(convertToCompany(results));
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return companies;
  }

  /** Convert the first element of a resultSet to the company inside.
   * 
   * @param results : the result of a query
   * @return the company corresponding
   */
  public Company convertToCompany(ResultSet results) {
    Company company = new Company();
    try {
      results.next();
      company.setId(results.getInt("Id"));
      company.setName(results.getString("Name"));
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return company;
  }

}
