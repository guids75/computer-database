package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
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

  @Override
  public List<Company> list(int nbElements, int offset) throws ConnectionException {
    return companyDao.list(nbElements, offset);
  }

  @Override
  public int getNumber() throws ConnectionException {
    return companyDao.getNumber();
  }

  @Override
  public Company getCompany(int id) throws ConnectionException {
    return companyDao.getCompany(id);
  }

}
