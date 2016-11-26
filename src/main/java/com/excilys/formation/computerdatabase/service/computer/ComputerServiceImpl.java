package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.computer.computerImpl.ComputerDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum ComputerServiceImpl implements ComputerService {

  INSTANCE;
  private static final ComputerDaoImpl computerDao = 
      ComputerDaoImpl.INSTANCE; // dao for Computer to manage the computers
  private static HikariConnectionPool hikariConnectionPool = 
      HikariConnectionPool.INSTANCE; // get the connection
  private static final Logger slf4jLogger = LoggerFactory.getLogger(ComputerServiceImpl.class);

  @Override
  public Computer insert(Computer computer) {
    try {
      return computerDao.insert(computer);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in inserty");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public Computer update(Computer computer) {
    try {
      return computerDao.update(computer);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in update");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public void delete(Constraints constraints) {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection()) {
      computerDao.delete(constraints, connection);
    } catch (SQLException | ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in delete");
      slf4jLogger.error(exception.getMessage());
    }
  }

  @Override
  public List<Computer> list(Constraints constraints) {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection()) {
      return computerDao.list(constraints, connection);
    } catch (SQLException | ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in list");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public void showComputerDetails(long computerId) {
    try {
      computerDao.showComputerDetails(computerId);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in showComputerDetails");
      slf4jLogger.error(exception.getMessage());
    }
  }

  @Override
  public int count(Constraints constraints) {
    try {
      return computerDao.count(constraints);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in count");
      slf4jLogger.error(exception.getMessage());
    }
    return -1;
  }

  @Override
  public List<Computer> search(Constraints constraints) {
    try {
      return computerDao.search(constraints);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in search");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

}
