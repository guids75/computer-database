package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GUIDS
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private DataSource dataSource; // get the connection
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private ComputerDao computerDao;
    
    
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

    @Override
    //@Transactional
    public void delete(Constraints constraints) {
        System.out.println(1);
            constraints.setIdList(computerDao.listByCompany(constraints));
        System.out.println(2);
            computerDao.delete(constraints);
        System.out.println(3);
            companyDao.delete(constraints);
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
