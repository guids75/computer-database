package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultToObjectMapper {

  private static LocalDateMapper localMapper = LocalDateMapper.getInstance();

  private static ResultToObjectMapper resultToObjectMapper = new ResultToObjectMapper();

  private ResultToObjectMapper() {
  }

  public static ResultToObjectMapper getInstance() {
    return resultToObjectMapper;
  }

  /**
   * 
   * @param results
   * @return
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

  /**
   * 
   * @param results
   * @return
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

  /**
   * 
   * @param results
   * @return
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

  /**
   * 
   * @param results
   * @return
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
