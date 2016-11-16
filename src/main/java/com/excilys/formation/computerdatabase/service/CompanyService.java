package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.CompanyDao;

import java.util.List;

/**
 * @author GUIDS
 *
 */
public class CompanyService implements CompanyServiceInterface {

  private static final CompanyDao companyDao = CompanyDao.getInstance(); //dao of Company to manage the companies
  private static CompanyService companyService = new CompanyService(); //singleton of this class

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

  public List<Company> list(int nbElements, int offset) {
    return companyDao.list(nbElements, offset);
  }

  public int getNumber() {
    return companyDao.getNumber();
  }

  public Company getCompany(int id) {
    return companyDao.getCompany(id);
  }

}
