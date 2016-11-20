package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;

/**
 * @author GUIDS
 *
 * @param Company : type managed by the interface
 */
public interface CompanyDaoInterface extends DaoInterface<Company> {

  /** Get the company with its id.
   * 
   * @param id : the id of the company
   * @return the company specified
   */
  public Company getCompany(int id) throws ConnectionException;

  /**
   * @return the number of companies
   */
  public int count() throws ConnectionException;

}
