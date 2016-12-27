package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GUIDS
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private ComputerDao computerDao;

    
    public  CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }


    @Override
    public List<Company> list(Constraints constraints) {
        return companyDao.list(constraints);
    }

    @Override
    public void delete(Constraints constraints) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try { 
                constraints.setIdList(computerDao.listByCompany(constraints, session));
                computerDao.delete(constraints, session);
                companyDao.delete(constraints, session);
            } catch (Exception exception) {
                transaction.rollback();
                throw new ConnectionException("Transaction problem when deleting a company",exception);
            }
        }
    }

    @Override
    public int count() {
        return companyDao.count();
    }

    @Override
    public Company getCompany(Long id) {
        return companyDao.getCompany(id);
    }

}
