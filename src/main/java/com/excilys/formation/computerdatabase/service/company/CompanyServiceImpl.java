package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.company.companyImpl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public class CompanyServiceImpl implements CompanyService {

  private static final CompanyDaoImpl companyDao = CompanyDaoImpl.getInstance(); //dao of Company to manage the companies
  private static CompanyServiceImpl companyService = new CompanyServiceImpl(); //singleton of this class
  private static final Logger slf4jLogger = LoggerFactory.getLogger(CompanyServiceImpl.class);

  /**
   * Private constructor for singleton
   */
  private CompanyServiceImpl() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static CompanyServiceImpl getInstance() {
    return companyService;
  }

  @Override
  public List<CompanyDto> list(int nbElements, int offset) {
    try {
      return CompanyDtoMapper.companyListToCompanyDtoList(companyDao.list(nbElements, offset));
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
  public CompanyDto getCompany(int id) {
    try {
      return CompanyDtoMapper.companyToCompanyDto(companyDao.getCompany(id));
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyService in getCompany");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

}
