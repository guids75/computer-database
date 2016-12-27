package com.excilys.formation.computerdatabase;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.excilys.formation.computerdatabase.model.Company;

public class TestHibernate {

    public static void main(String args[]) throws Exception {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession(); 

        Transaction tx = null; 
        try { 
            tx = session.beginTransaction(); 
            Company company = new Company.Builder("mynewcompany").build();
            session.save(company);
            session.flush() ;
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally { 
            session.close(); 
        } 
    }
    
}
