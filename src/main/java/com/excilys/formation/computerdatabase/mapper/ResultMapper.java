package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public final class ResultMapper {

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //to convert localdate to date
  private static final Logger slf4jLogger = LoggerFactory.getLogger(ResultMapper.class);

  /** Convert a resultSet to the list of computers inside.
   * 
   * @param results : the result of a query
   * @return the list of computers corresponding
   */
  public static List<Computer> convertToComputers(ResultSet results) {
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
  public static Computer convertToComputer(ResultSet results) {
    try {
      ComputerBuilder computer = new Computer.ComputerBuilder(results.getString("comput.Name"))
          .id(results.getInt("comput.Id"));
          if (results.getDate("comput.Introduced") != null) {
            computer.introduced(LocalDate.parse(simpleDateFormat.format(results.getDate("comput.Introduced"))));
          }
          if (results.getDate("comput.Discontinued") != null) {
            computer.discontinued(LocalDate.parse(simpleDateFormat.format(results.getDate("comput.Discontinued"))));
          }
          
          computer.company(new Company.CompanyBuilder(results.getString("compan.Name"))
              .id(results.getInt("compan.id")).build());
      return computer.build();
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
  public static List<Company> convertToCompanies(ResultSet results) {
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
  public static Company convertToCompany(ResultSet results) {
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
