package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.ComputerDao;

import java.util.List;

/**
 * @author GUIDS
 *
 */
public class ComputerService implements ComputerServiceInterface {

  private static final ComputerDao computerDao = ComputerDao.getInstance(); //dao for Computer to manage the computers
  private static ComputerService computerService = new ComputerService(); //singleton of this class

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
  public Computer insert(Computer computer) throws ConnectionException {
    return computerDao.insert(computer);
  }

  @Override
  public Computer update(Computer computer) throws ConnectionException {
    return computerDao.update(computer);
  }

  @Override
  public void delete(int computer) throws ConnectionException {
    computerDao.delete(computer);
  }

  @Override
  public List<Computer> list(int nbElements, int offset) throws ConnectionException {
    return computerDao.list(nbElements, offset);
  }

  @Override
  public void showComputerDetails(int computerId) throws ConnectionException {
    computerDao.showComputerDetails(computerId);
  }

  public int getNumber() throws ConnectionException {
    return computerDao.getNumber();
  }

}
