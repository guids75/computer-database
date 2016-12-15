package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

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
    @Autowired
    private DataSource dataSource; // get the connection
    private static final Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);
    
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }

    
    @Override
    public Computer insert(Computer computer) {
        return computerDao.insert(computer);
    }

    @Override
    public Computer update(Computer computer) {
        return computerDao.update(computer);
    }

    @Override
    public void delete(Constraints constraints) {
        try (Connection connection = dataSource.getConnection()){
            computerDao.delete(constraints);
        } catch (SQLException exception) {
            logger.error("Error in ComputerService in delete", exception);
        }
    }

    @Override
    public List<Computer> list(Constraints constraints) {
        return computerDao.list(constraints);
    }

    @Override
    public void showComputerDetails(Long computerId) {
        computerDao.showComputerDetails(computerId);
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
