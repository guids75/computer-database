package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.ui.Cli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GUIDS
 *
 */
public class CompanyServiceImpl implements CompanyService {

    private DataSource dataSource; // get the connection
    private CompanyDao companyDao;
    private ComputerDao computerDao;
    private Connection connection;
    
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    /*@Override
    public void delete(Constraints constraints) {
        try (Connection connection = dataSource.beginTransaction()){
            constraints.setIdList(computerDao.listByCompany(constraints, connection));
            computerDao.delete(constraints, connection);
            companyDao.delete(constraints, connection);
            dataSource.endTransaction();
        } catch (SQLException exception) {
            dataSource.rollBackTransaction();
            throw new ConnectionException("companies failed to be counted", exception);
        }
    }*/

    @Override
    public int count() {
        return companyDao.count();
    }

    @Override
    public Company getCompany(Long id) {
        return companyDao.getCompany(id);
    }

}
