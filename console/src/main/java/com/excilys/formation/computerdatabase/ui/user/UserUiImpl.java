package com.excilys.formation.computerdatabase.ui.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.model.User;
import com.excilys.formation.computerdatabase.model.UserRole;
import com.excilys.formation.computerdatabase.service.user.UserService;

@Controller
public class UserUiImpl implements UserUi {

    @Autowired
    private UserService userService; // service of Company to manage them
    
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);
    
    /**
     * Create a new instance of Page and set their number according to the number
     * of companies.
     * 
     */
    public UserUiImpl(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void list() {
        print(userService.list(null));
    }

    /**
     * Print a list of computers.
     * 
     * @param computers
     *          : list of computers to print.
     */
    public void print(List<User> users) {
        users.forEach(user-> System.out.println(user));
    }

    @Override
    public void insert() {
        System.out.println("which login?");
        String login = scanner.nextLine();
        System.out.println("which password?");
        String password = scanner.nextLine();
        
        User user = new User.Builder(login).password(password).userRole(UserRole.ROLE_USER).build();
        
        try {
            userService.insert(user);
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("insert in userService is not implemented yet", exception);
        }
    }

    @Override
    public void delete() {
        System.out.println("which user id?");
        ArrayList<Long> idList = new ArrayList<Long>();
        idList.add(scanner.nextLong());
        try {
            userService.delete(new Constraints.ConstraintsBuilder().idList(idList).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in computerService is not implemented yet", exception);
        }
        scanner.nextLine();
    }
    
}
