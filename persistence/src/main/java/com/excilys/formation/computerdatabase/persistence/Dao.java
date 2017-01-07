package com.excilys.formation.computerdatabase.persistence;

import java.util.List;

import org.hibernate.Session;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.constraints.Constraints;

/**
 * @author GUIDS
 *
 * @param <T>
 *            : the object of the Dao
 */
public interface Dao<T> {

    /**
     * Create a T object in the service.
     * 
     * @param object
     *            : the object to create
     * @return : the T object created
     */
    public default Long insert(T object) throws ConnectionException {
        System.out.println("Default implementation of create");
        return null;
    }

    /**
     * Update a T object in the service.
     * 
     * @param object
     *            : the object to update
     * @return : the T object updated
     */
    public default T update(T object) throws ConnectionException {
        System.out.println("Default implementation of update");
        return null;
    }

    /**
     * Delete a T object in the service.
     * 
     * @param idObject
     *            : the id of the T object to delete
     */
    public default void delete(Constraints constraints) throws ConnectionException {
        System.out.println("Default implementation of delete");
    }

    /**
     * Get the list of all the T objects in the service.
     * 
     * @param nbElements
     *            : the number of T objects in the service
     * @param offset
     *            : the offset to display
     * @return : the list of all the T objects in the service
     */
    public List<T> list(Constraints constraints) throws ConnectionException;

}
