package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author GUIDS
 *
 * @param Computer : type managed by the interface
 */
public interface ComputerDaoInterface extends DaoInterface<Computer> {

  /** Show all the attributes of a specific computer.
   * 
   * @param computerId : the id of the computer
   * @return the computer specified
   */
  public Computer showComputerDetails(int computerId);

  /**
   * @return the number of computers
   */
  public int getNumber();
}
