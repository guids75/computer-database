package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.service.Service;

/**
 * @author GUIDS
 *
 * @param <T> : Computer
 */
public interface ComputerService extends Service<ComputerDto> {

  /** Show all the attributes of the specified computer.
   * 
   * @param computerId : the id of the computer
   */
  public void showComputerDetails(int computerId);

  /**
   * @return the number of computers
   */
  public int count();

}
