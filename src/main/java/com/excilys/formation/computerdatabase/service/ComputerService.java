package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.ComputerDao;

import java.util.List;

public class ComputerService implements ComputerServiceInterface<Computer> {

  private static final ComputerDao computerDao = ComputerDao.getInstance();
  
  private ComputerService() {
  }
  
  private static ComputerService computerService = new ComputerService();
  
  public static ComputerService getInstance() {
    return computerService;
  }

  public Computer create(Computer computer) {
    return computerDao.create(computer);
  }

  public Computer update(Computer computer) {
    return computerDao.update(computer);
  }

  public void delete(int computer) {
    computerDao.delete(computer);
  }

  public List<Computer> list(int nbElements, int offset, boolean write) {
    return computerDao.list(nbElements, offset, write);
  }

  public void showComputerDetails(int computer) {
    computerDao.showComputerDetails(computer);
  }
    
}
