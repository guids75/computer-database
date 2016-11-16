package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Company;

/**
 * @author GUIDS
 *
 * @param <T> : Company
 */
public interface CompanyServiceInterface extends ServiceInterface<Company> {

  /**
   * @return the number of companies
   */
  public int getNumber();

  /**
   * @param id : the id of the company
   * @return the company specified
   */
  public Company getCompany(int id);
}
