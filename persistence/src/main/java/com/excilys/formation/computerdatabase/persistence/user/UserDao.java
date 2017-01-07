package com.excilys.formation.computerdatabase.persistence.user;

import com.excilys.formation.computerdatabase.model.User;
import com.excilys.formation.computerdatabase.persistence.Dao;

/**
 * @author GUIDS
 *
 * @param Computer
 *            : type managed by the interface
 */
public interface UserDao extends Dao<User> {

    public User getByName(String name);
    
}