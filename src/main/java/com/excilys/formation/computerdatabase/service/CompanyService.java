package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.CompanyDao;

import java.util.List;

public class CompanyService implements CompanyServiceInterface<Company> {

  private static final CompanyDao companyDao = CompanyDao.getInstance();
  
  private CompanyService() {
  }
  
  private static CompanyService companyService = new CompanyService();
  
  public static CompanyService getInstance() {
    return companyService;
  }

  @Override
  public List<Company> list(int nbElements, int offset, boolean write) {
    return companyDao.list(nbElements, offset, write);
  }
  
}
