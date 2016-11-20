package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.ComputerDao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public class ComputerService implements ComputerServiceInterface {

  private static final ComputerDao computerDao = ComputerDao.getInstance(); //dao for Computer to manage the computers
  private static ComputerService computerService = new ComputerService(); //singleton of this class
  private static final Logger slf4jLogger = LoggerFactory.getLogger(ComputerService.class);

  /**
   * Private constructor for singleton.
   */
  private ComputerService() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static ComputerService getInstance() {
    return computerService;
  }

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
  public void delete(int computer) {
    try {
      computerDao.delete(computer);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in delete");
      slf4jLogger.error(exception.getMessage());
    }
  }

  @Override
  public List<Computer> list(int nbElements, int offset) {
    try {
      return computerDao.list(nbElements, offset);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in list");
      slf4jLogger.error(exception.getMessage());
    }
    return null;
  }

  @Override
  public void showComputerDetails(int computerId) {
    try {
      computerDao.showComputerDetails(computerId);
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in showComputerDetails");
      slf4jLogger.error(exception.getMessage());
    }
  }

  @Override
  public int count() {
    try {
      return computerDao.count();
    } catch (ConnectionException exception) {
      slf4jLogger.error("Error in ComputerService in count");
      slf4jLogger.error(exception.getMessage());
    }
    return -1;
  }

}
