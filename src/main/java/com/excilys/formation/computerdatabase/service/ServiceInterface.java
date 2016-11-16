package com.excilys.formation.computerdatabase.service;

import java.util.List;

/**
 * @author GUIDS
 *
 * @param <T> : the object of the service
 */
public interface ServiceInterface<T> {

  /** Create a T object in the database. 
   * 
   * @param object : the object to create
   * @return : the T object created
   */
  public default T create(T object) {
    System.out.println("Default implementation of create");
    return null;
  }

  /** Update a T object in the database.
   * 
   * @param object : the object to update
   * @return : the T object updated
   */
  public default T update(T object) {
    System.out.println("Default implementation of update");
    return null;
  }

  /** Delete a T object in the database.
   * 
   * @param object : the T object to delete
   */
  public default void delete(T object) {
    System.out.println("Default implementation of delete");
  }

  /** Get the list of all the T objects in the database.
   * 
   * @param nbElements : the number of T objects in the database
   * @param offset : the offset to display
   * @return : the list of all the T objects in the database
   */
  public List<T> list(int nbElements, int offset); 

}
