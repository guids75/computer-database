package com.excilys.formation.computerdatabase.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.excilys.formation.computerdatabase.model.User;
import com.excilys.formation.computerdatabase.service.Service;

public interface UserService extends UserDetailsService, Service<User> {

}
