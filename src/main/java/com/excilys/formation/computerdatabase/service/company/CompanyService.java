package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.service.Service;

/**
 * @author GUIDS
 *
 * @param <T> : Company
 */
public interface CompanyService extends Service<CompanyDto> {
  
  /**
   * @return the number of companies
   */
  public int count();

  /**
   * @param id : the id of the company
   * @return the company specified
   */
  public CompanyDto getCompany(int id);
}
