package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.CompanyDao;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public class CompanyService implements CompanyServiceInterface {

  private static final CompanyDao companyDao = CompanyDao.getInstance(); //dao of Company to manage the companies
  private static CompanyService companyService = new CompanyService(); //singleton of this class
  private static final Logger slf4jLogger = LoggerFactory.getLogger(CompanyService.class);

  /**
   * Private constructor for singleton
   */
  private CompanyService() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static CompanyService getInstance() {
    return companyService;
  }

  @Override
  public List<Company> list(int nbElements, int offset) {
    try {
      return companyDao.list(nbElements, offset);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyService in list");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public int count() {
    try {
      return companyDao.count();
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyList in count");
      slf4jLogger.error(exception.getMessage());
      }
    return -1;
  }

  @Override
  public Company getCompany(int id) {
    try {
      return companyDao.getCompany(id);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyService in getCompany");
      slf4jLogger.error(exception.getMessage());
      }
    return null;
  }

}
