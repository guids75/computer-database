package com.excilys.formation.computerdatabase.service;

public interface ComputerServiceInterface<T> extends ServiceInterface<T> {

  public void showComputerDetails(int computerId);
  
	public int getNumber();

}
