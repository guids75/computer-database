package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author GUIDS
 *
 * @param <T> : Computer
 */
public interface ComputerServiceInterface extends ServiceInterface<Computer> {

  /** Show all the attributes of the specified computer.
   * 
   * @param computerId : the id of the computer
   */
  public void showComputerDetails(int computerId) throws ConnectionException;

  /**
   * @return the number of computers
   */
  public int getNumber() throws ConnectionException;

}
