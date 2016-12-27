package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GUIDS
 *
 */
@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerDao computerDao; // dao for Computer to manage the computers
    private static final Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }


    @Override
    public Long insert(Computer computer) {
        return computerDao.insert(computer);
    }

    @Override
    public Computer update(Computer computer) {
        return computerDao.update(computer);
    }

    @Override
    public void delete(Constraints constraints) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                computerDao.delete(constraints, session);
            } catch (Exception exception) {
                transaction.rollback();
                throw new ConnectionException("Transaction problem when deleting a company",exception);
            }
        }
    }

        @Override
        public List<Computer> list(Constraints constraints) {
            return computerDao.list(constraints);
        }

        @Override
        public void showComputerDetails(Long computerId) {
            System.out.println(computerDao.showComputerDetails(computerId));
        }

        @Override
        public int count(Constraints constraints) {
            return computerDao.count(constraints);
        }

        @Override
        public List<Computer> search(Constraints constraints) {
            return computerDao.search(constraints);
        }

    }
