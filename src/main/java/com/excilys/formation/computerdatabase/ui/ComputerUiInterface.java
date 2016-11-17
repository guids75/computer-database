package com.excilys.formation.computerdatabase.ui;

import com.excilys.formation.computerdatabase.exception.ConnectionException;

public interface ComputerUiInterface extends UiInterface {

  /** Show all the computer's attributes.
   * 
   */
  public void showComputerDetails() throws ConnectionException;

  /** Insert a computer in the database.
   * 
   */
  public void insert() throws ConnectionException;

  /** Update a computer.
   * 
   */
  public void update() throws ConnectionException;

  /** Delete a computer from the database.
   * 
   */
  public void delete() throws ConnectionException;

}
