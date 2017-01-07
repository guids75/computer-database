package com.excilys.formation.computerdatabase.service;

import java.util.List;

import org.hibernate.Session;

import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.constraints.Constraints;

/**
 * @author GUIDS
 *
 * @param <T>
 *            : the object of the service
 */
public interface Service<T> {

    /**
     * Create a T object in the service.
     * 
     * @param object
     *            : the object to create
     * @return : the T object created
     */
    public default Long insert(T object) throws NotImplementedMethodException {
        throw new NotImplementedMethodException("Default implementation of create");
    }

    /**
     * Update a T object in the service.
     * 
     * @param object
     *            : the object to update
     * @return : the T object updated
     */
    public default T update(T object) throws NotImplementedMethodException {
        throw new NotImplementedMethodException("Default implementation of update");
    }

    /**
     * Delete a T object in the service.
     * 
     * @param objectId
     *            : the id of the T object to delete
     */
    public default void delete(Constraints constraints) throws NotImplementedMethodException {
        throw new NotImplementedMethodException("Default implementation of delete");
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
    public List<T> list(Constraints constraints);

}
