package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.company.companyImpl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;
import com.excilys.formation.computerdatabase.persistence.computer.computerImpl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum CompanyServiceImpl implements CompanyService {

    INSTANCE;
    private static HikariConnectionPool hikariConnectionPool = 
            HikariConnectionPool.INSTANCE; // get the connection
    private static final CompanyDao companyDao = 
            CompanyDaoImpl.INSTANCE; // dao of Company to manage the companies
    private static final ComputerDao computerDao = 
            ComputerDaoImpl.INSTANCE; // dao of Company to manage the companies
    private Connection connection;

    @Override
    public List<Company> list(Constraints constraints) {
        return companyDao.list(constraints);
    }

    @Override
    public void delete(Constraints constraints) {
        try (Connection connection = hikariConnectionPool.beginTransaction()){
            constraints.setIdList(computerDao.listByCompany(constraints, connection));
            computerDao.delete(constraints, connection);
            companyDao.delete(constraints, connection);
            hikariConnectionPool.endTransaction();
        } catch (SQLException exception) {
            hikariConnectionPool.rollBackTransaction();
            throw new ConnectionException("companies failed to be counted", exception);
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
