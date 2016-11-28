package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.company.companyImpl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persistence.computer.computerImpl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum CompanyServiceImpl implements CompanyService {

  INSTANCE;
  private static HikariConnectionPool hikariConnectionPool = 
      HikariConnectionPool.INSTANCE; // get the connection
  private static final CompanyDaoImpl companyDao = 
      CompanyDaoImpl.INSTANCE; // dao of Company to manage the companies
  private static final ComputerDaoImpl computerDao = 
      ComputerDaoImpl.INSTANCE; // dao of Company to manage the companies
  private static final Logger slf4jLogger = LoggerFactory.getLogger(CompanyServiceImpl.class);
  private static Connection connection;
  
  static {
    try {
      connection = hikariConnectionPool.getDataSource().getConnection();
    } catch (SQLException exception) {
      slf4jLogger.error("Error in CompanyServiceImpl", exception);
    }
  }

  @Override
  public List<Company> list(Constraints constraints) {
    try {
      return companyDao.list(constraints, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyService in list", exception);
    }
    return null;
  }

  @Override
  public void delete(Constraints constraints) {
    try {
      connection.setAutoCommit(false);
      constraints.setIdList(computerDao.listByCompany(constraints, connection));
      computerDao.delete(constraints, connection);
      companyDao.delete(constraints, connection);
      connection.commit();
    } catch (SQLException exception) {
      try {
        connection.rollback();
        connection.setAutoCommit(true);
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " + sqx.getMessage());
      }
      throw new ConnectionException("companies failed to be counted", exception);
    }
  }

  @Override
  public int count() {
    try {
      return companyDao.count(connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyList in count");
      slf4jLogger.error(exception.getMessage());
    }
    return -1;
  }

  @Override
  public Company getCompany(long id) {
    try {
      return companyDao.getCompany(id, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in CompanyService in getCompany");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

}
