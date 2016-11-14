package com.excilys.formation.computerdatabase.ui;

import com.excilys.formation.computerdatabase.model.Page;

public interface ComputerUiInterface extends UiInterface {

  public void showComputerDetails();

  public void insert(Page p);

  public void update(Page p);

  public void delete(Page p);
  
}
