package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;
import com.excilys.formation.computerdatabase.persistence.computer.computerImpl.ComputerDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum ComputerServiceImpl implements ComputerService {

    INSTANCE;
    private static final ComputerDao computerDao = 
            ComputerDaoImpl.INSTANCE; // dao for Computer to manage the computers
    private static HikariConnectionPool hikariConnectionPool = 
            HikariConnectionPool.INSTANCE; // get the connection
    private static final Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);

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
        try (Connection connection = hikariConnectionPool.getConnection()){
            computerDao.delete(constraints, connection);
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
