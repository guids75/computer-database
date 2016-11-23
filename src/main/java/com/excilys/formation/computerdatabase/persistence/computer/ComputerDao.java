package com.excilys.formation.computerdatabase.persistence.computer;

import java.util.List;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Dao;

/**
 * @author GUIDS
 *
 * @param Computer : type managed by the interface
 */
public interface ComputerDao extends Dao<Computer> {

  /** Show all the attributes of a specific computer.
   * 
   * @param computerId : the id of the computer
   * @return the computer specified
   */
  public Computer showComputerDetails(int computerId) throws ConnectionException;

  /**
   * @return the number of computers
   */
  public int count() throws ConnectionException;

  /**
   * @param nbElements
   * @param offset
   * @return
   * @throws ConnectionException
   */
  public List<Computer> search(String search, int nbElements, int offset) throws ConnectionException; 

}