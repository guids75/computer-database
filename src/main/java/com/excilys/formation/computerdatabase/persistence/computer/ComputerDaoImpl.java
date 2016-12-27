package com.excilys.formation.computerdatabase.persistence.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;

import java.util.List;

/**
 * @author GUIDS
 *
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

    // requests
    private static final String UPDATE_REQUEST = "UPDATE Computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:company_id WHERE id=:id";
    private static final String DELETE_REQUEST = "DELETE FROM Computer AS computer WHERE computer.id";
    private static final String LIST_REQUEST = "SELECT computer FROM Computer AS computer LEFT JOIN computer.company AS company";
    private static final String DETAILS_REQUEST = "SELECT computer FROM Computer AS computer LEFT JOIN computer.company AS company WHERE computer.id=:id";
    private static final String NUMBER_REQUEST = "SELECT COUNT(computer.id) AS number FROM Computer AS computer";
    private static final String SEARCH_REQUEST = "SELECT computer FROM Computer AS computer LEFT JOIN computer.company AS company WHERE computer.name LIKE :search OR company.name LIKE :search";
    private static final String LISTBYCOMPANY_REQUEST = "SELECT computer.id FROM Computer AS computer LEFT JOIN computer.company AS company WHERE company.id=:id";

    public ComputerDaoImpl() {
    }

    @Override
    public Long insert(Computer computer) throws ConnectionException {
        if (computer == null) {
            throw new IllegalArgumentException("A computer is missing to insert");
        }

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            return (Long) session.save(computer);
        }
    }

    @Override
    public Computer update(Computer computer) throws ConnectionException {
        if (computer == null) {
            throw new IllegalArgumentException("A computer is missing to update");
        }
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try { 
                Query query = session.createQuery(UPDATE_REQUEST);
                query.setParameter("name", computer.getName());
                query.setParameter("introduced", computer.getIntroduced());
                query.setParameter("discontinued", computer.getDiscontinued());
                query.setParameter("company_id", computer.getCompany().getId());
                query.setParameter("id", computer.getId());
                query.executeUpdate();
                transaction.commit();
            } catch (Exception exception) {
                transaction.rollback();
                throw new ConnectionException("Transaction problem when updating a computer",exception);
            }
            return computer;
        }
    }

    @Override
    public void delete(Constraints constraints, Session session) throws ConnectionException {
        if (constraints == null | (constraints.getIdList() == null)) {
            throw new IllegalArgumentException("A connection is missing to delete or the constraints are null");
        }
        if (constraints.getIdList().isEmpty()) {
            return;
        }
        String request;
        Query query;
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
    public List<Computer> list(Constraints constraints) throws ConnectionException {
        if (constraints == null | (constraints.getLimit() == -1 || constraints.getOffset() == -1)) {
            throw new IllegalArgumentException("Constraints are missing to list");
        }
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            String request = LIST_REQUEST;
            if (constraints.getOrderBy() != null) {
                request += " ORDER BY " + constraints.getOrderBy() + " ASC";
            }
            Query<Computer> query = session.createQuery(request, Computer.class);
            query.setFirstResult(constraints.getOffset());
            query.setMaxResults(constraints.getLimit());
            return query.getResultList();
        }
    }

    @Override
    public List<Long> listByCompany(Constraints constraints, Session session) throws ConnectionException {
        if (constraints == null | (constraints.getIdCompany() == -1)) {
            throw new IllegalArgumentException("Constraints are missing to listByCompany");
        }
        Query<Long> query = session.createQuery(LISTBYCOMPANY_REQUEST, Long.class);
        query.setParameter("id", constraints.getIdCompany());
        return query.getResultList();
    }

    @Override
    public Computer showComputerDetails(Long computerId) throws ConnectionException {
        if (computerId < 1) {
            throw new IllegalArgumentException("The computerId is wrong to showComputerDetails : must be more than 0");
        }
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Query<Computer> query = session.createQuery(DETAILS_REQUEST, Computer.class);
            query.setParameter("id", computerId);
            return query.getSingleResult();
        }
    }

    @Override
    public int count(Constraints constraints) throws ConnectionException {
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints are missing to count");
        }
        String request;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            if (constraints.getSearch() != null && !constraints.getSearch().equals("")) {
                request = "SELECT COUNT(computer.id) FROM Computer AS computer WHERE computer IN (" + SEARCH_REQUEST + ")";
            } else {
                request = NUMBER_REQUEST;
            }
            Query<Long> query = session.createQuery(request, Long.class);
            if (constraints.getSearch() != null && !constraints.getSearch().equals("")) {
                query.setParameter("search", "%" + constraints.getSearch() + "%");
            }
            return query.getSingleResult().intValue();
        }
    }

    @Override
    public List<Computer> search(Constraints constraints) throws ConnectionException {
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints are missing to search");
        }
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Query<Computer> query = session.createQuery(SEARCH_REQUEST, Computer.class);
            query.setParameter("search", "%" + constraints.getSearch() + "%");
            query.setFirstResult(constraints.getOffset());
            query.setMaxResults(constraints.getLimit());
            System.out.println( " ici " + query.getQueryString());
            return query.getResultList();
        }
    }

}
