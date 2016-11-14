package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.model.Computer;

public interface ComputerDaoInterface<T> extends DaoInterface<T> {

  public Computer showComputerDetails(int computerId);
  
}
