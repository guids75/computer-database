package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.persistence.Constraints;

/**
 * @author GUIDS
 *
 * @param <T>
 *          : the object of the service
 */
public interface Service<T> {

  /**
   * Create a T object in the database.
   * 
   * @param object
   *          : the object to create
   * @return : the T object created
   */
  public default T insert(T object) throws NotImplementedMethodException {
    throw new NotImplementedMethodException("Default implementation of create");
  }

  /**
   * Update a T object in the database.
   * 
   * @param object
   *          : the object to update
   * @return : the T object updated
   */
  public default T update(T object) throws NotImplementedMethodException {
    throw new NotImplementedMethodException("Default implementation of update");
  }

  /**
   * Delete a T object in the database.
   * 
   * @param objectId
   *          : the id of the T object to delete
   */
  public default void delete(Constraints constraints) throws NotImplementedMethodException {
    throw new NotImplementedMethodException("Default implementation of delete");
  }

  /**
   * Get the list of all the T objects in the database.
   * 
   * @param nbElements
   *          : the number of T objects in the database
   * @param offset
   *          : the offset to display
   * @return : the list of all the T objects in the database
   */
  public List<T> list(Constraints constraints);

}
