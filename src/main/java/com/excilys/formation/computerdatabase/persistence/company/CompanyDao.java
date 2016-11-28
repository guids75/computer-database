package com.excilys.formation.computerdatabase.persistence.company;

import java.sql.Connection;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Dao;

/**
 * @author GUIDS
 *
 * @param Company
 *          : type managed by the interface
 */
public interface CompanyDao extends Dao<Company> {

  /**
   * Get the company with its id.
   * 
   * @param id
   *          : the id of the company
   * @return the company specified
   */
  public Company getCompany(long id, Connection connection) throws ConnectionException;

  /**
   * @return the number of companies
   */
  public int count(Connection connection) throws ConnectionException;

}
