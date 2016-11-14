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
  public List<Computer> convertToComputers(ResultSet results, boolean write) {
    List<Computer> computers = new ArrayList<>();
    try {
      while (!results.isLast()) {
        computers.add(convertToComputer(results, write));
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
  public Computer convertToComputer(ResultSet results, boolean write) {
    try {
      results.next();
      Computer computer = new Computer.ComputerBuilder(1, 
          results.getString("comput.Name"), new Company(results.getInt("compan.Id"), results.getString("compan.Name")))
          .introduced(localMapper.convertToLocalDate(results.getDate("comput.Introduced")))
          .discontinued(localMapper.convertToLocalDate(results.getDate("comput.Discontinued")))
          .build();
      if (write) {
        System.out.println("Computer " + computer.getName() + " : " + computer.getId());
      }
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
  public List<Company> convertToCompanies(ResultSet results, boolean write) {
    List<Company> companies = new ArrayList<>();
    try {
      while (!results.isLast()) {
        companies.add(convertToCompany(results, write));
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
  public Company convertToCompany(ResultSet results, boolean write) {
    Company company = new Company();
    try {
      results.next();
      company.setId(results.getInt("Id"));
      company.setName(results.getString("Name"));
      if (write) {
        System.out.println("Company " + company.getName() + " : " + company.getId());
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return company;
  }

}
