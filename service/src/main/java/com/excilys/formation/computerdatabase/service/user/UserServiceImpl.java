package com.excilys.formation.computerdatabase.service.user;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.User;
import com.excilys.formation.computerdatabase.model.UserRole;
import com.excilys.formation.computerdatabase.persistence.user.UserDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author GUIDS
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // dao for Computer to manage the computers

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Constraints constraints) {
        userDao.delete(constraints);
    }

    @Override
    public List<User> list(Constraints constraints) {
        return userDao.list(constraints);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        User user = userDao.getByName(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);

    }

    // Converts my User user to
    // org.springframework.security.core.userdetails.User
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
            List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();      
        setAuths.add(new SimpleGrantedAuthority(userRole.getValue()));
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        return Result;
    }

}
