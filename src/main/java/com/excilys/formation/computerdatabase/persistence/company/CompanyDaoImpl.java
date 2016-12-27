package com.excilys.formation.computerdatabase.persistence.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.CompanyJdbcMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author GUIDS
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    // requests
    private static final String LIST_REQUEST = "FROM Company AS company";
    private static final String DELETE_REQUEST = "DELETE FROM Company AS company WHERE company.id=:id";
    private static final String NUMBER_REQUEST = "SELECT COUNT(company.id) AS number FROM Company AS company";
    private static final String COMPANY_REQUEST = "FROM Company as company WHERE company.id=:id";

    public CompanyDaoImpl() {
    }

    @Override
    public List<Company> list(Constraints constraints) throws ConnectionException {
        if (constraints == null || (constraints.getLimit() == -1 || constraints.getOffset() == -1)) {
            throw new IllegalArgumentException("A limit or an offset is missing in the constraints");
        }
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Query<Company> query = session.createQuery(LIST_REQUEST, Company.class);
            query.setFirstResult(constraints.getOffset());
            query.setMaxResults(constraints.getLimit());
            return query.getResultList();
        }
    }

    @Override
    public void delete(Constraints constraints, Session session) throws ConnectionException {
        if (constraints == null || (constraints.getIdCompany() == -1L)) {
            throw new IllegalArgumentException("A company is missing in the constraints or the connection is closed");
        }
        Query query = session.createQuery(DELETE_REQUEST);
        query.setParameter("id", constraints.getIdCompany());
        query.executeUpdate();
    }

    @Override
    public int count() throws ConnectionException {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(NUMBER_REQUEST, Long.class);
            return  query.getSingleResult().intValue();
        }
    }

    @Override
    public Company getCompany(Long id) throws ConnectionException {
        if (id < 1) {
            throw new IllegalArgumentException("The id given for the company is wrong");
        }
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Query<Company> query = session.createQuery(COMPANY_REQUEST, Company.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

}
