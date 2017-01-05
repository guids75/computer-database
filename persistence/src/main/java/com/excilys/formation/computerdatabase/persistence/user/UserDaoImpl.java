package com.excilys.formation.computerdatabase.persistence.user;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.exception.ConnectionException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.model.User;

import java.util.List;

/**
 * @author GUIDS
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

    // requests
    private static final String DELETE_REQUEST = "DELETE FROM User AS user WHERE user.id";
    private static final String LIST_REQUEST = "SELECT user FROM User AS user";
    private static final String GET_REQUEST = "SELECT user FROM User AS user WHERE user.login=:login";

    @Autowired
    private SessionFactory sessionFactory;


    public Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception exception) {
            sessionFactory.openSession();
            return sessionFactory.getCurrentSession();
        }
    }


    @Override
    public Long insert(User user) throws ConnectionException {
        if (user == null) {
            throw new IllegalArgumentException("A user is missing to insert");
        }
        Session session = getSession();
        return (Long) session.save(user);
    }
    
    public User getByName(String login) throws ConnectionException {
        Session session = getSession();
        Query<User> query = session.createQuery(GET_REQUEST, User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    @Override
    public void delete(Constraints constraints) throws ConnectionException {
        if (constraints == null | constraints.getIdList() == null) {
            throw new IllegalArgumentException("A connection is missing to delete or the constraints are null");
        }
        if (constraints.getIdList().isEmpty()) {
            return;
        }
        String request;
        Query query;
        Session session = getSession();
        if (constraints.getIdList().size() == 1) {
            request = DELETE_REQUEST + "=:id";
            query = session.createQuery(request);
            query.setParameter("id", constraints.getIdList().get(0));
        } else {
            request = DELETE_REQUEST + " IN (:list)";
            query = session.createQuery(request);
            query.setParameter("list", constraints.getIdList());
        }
        query.executeUpdate();
    }

    @Override
    public List<User> list(Constraints constraints) throws ConnectionException {
        Session session = getSession();
        Query<User> query = session.createQuery(LIST_REQUEST, User.class);
        return query.getResultList();
    }

}
