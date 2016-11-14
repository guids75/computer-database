package com.excilys.formation.computerdatabase.service;

import java.util.List;

public interface ServiceInterface<T> {

  public default T create(T object) {
    System.out.println("Default implementation of create");
    return null;
  }
  
  public default T update(T object) {
    System.out.println("Default implementation of update");
    return null;
  }
  
  public default void delete(T object) {
    System.out.println("Default implementation of delete");
  }
  
  public List<T> list(int nbElements, int offset, boolean write); 
  
}
