package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.model.Company;

public interface CompanyDaoInterface<T> extends DaoInterface<T> {

  public Company getCompany(int id);
  
}
