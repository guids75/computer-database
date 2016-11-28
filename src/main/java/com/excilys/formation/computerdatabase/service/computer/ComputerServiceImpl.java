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
  private static Connection connection;
  
  static {
    try {
      connection = hikariConnectionPool.getDataSource().getConnection();
    } catch (SQLException exception) {
      slf4jLogger.error("Error in ComputerServiceImpl", exception);
    }
  }
  
  @Override
  public Computer insert(Computer computer) {
    try {
      return computerDao.insert(computer, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in insert", exception);
    }
    return null;
  }

  @Override
  public Computer update(Computer computer) {
    try {
      return computerDao.update(computer, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in update", exception);
    }
    return null;
  }

  @Override
  public void delete(Constraints constraints) {
    try  {
      computerDao.delete(constraints, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in delete", exception);
    }
  }

  @Override
  public List<Computer> list(Constraints constraints) {
    try {
      return computerDao.list(constraints, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in list", exception);
    }
    return null;
  }

  @Override
  public void showComputerDetails(long computerId) {
    try {
      computerDao.showComputerDetails(computerId, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in showComputerDetails", exception);
    }
  }

  @Override
  public int count(Constraints constraints) {
    try {
      return computerDao.count(constraints, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in count", exception);
    }
    return -1;
  }

  @Override
  public List<Computer> search(Constraints constraints) {
    try {
      return computerDao.search(constraints, connection);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in search", exception);
    }
    return null;
  }

}
