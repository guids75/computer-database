package com.excilys.formation.computerdatabase.ui;

import com.excilys.formation.computerdatabase.exception.ConnectionException;

public interface ComputerUiInterface extends UiInterface {

  /** Show all the computer's attributes.
   * 
   */
  public void showComputerDetails();

  /** Insert a computer in the database.
   * 
   */
  public void insert();

  /** Update a computer.
   * 
   */
  public void update();

  /** Delete a computer from the database.
   * 
   */
  public void delete();

}
