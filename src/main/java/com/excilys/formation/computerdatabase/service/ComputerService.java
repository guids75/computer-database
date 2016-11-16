package com.excilys.formation.computerdatabase.service;

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

  public Computer insert(Computer computer) {
    return computerDao.insert(computer);
  }

  public Computer update(Computer computer) {
    return computerDao.update(computer);
  }

  public void delete(int computer) {
    computerDao.delete(computer);
  }

  public List<Computer> list(int nbElements, int offset) {
    return computerDao.list(nbElements, offset);
  }

  public void showComputerDetails(int computer) {
    computerDao.showComputerDetails(computer);
  }

  public int getNumber() {
    return computerDao.getNumber();
  }

}
