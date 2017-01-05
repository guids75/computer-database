package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author GUIDS
 *
 */
@Service
@Transactional
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
        constraints.setIdList(computerDao.listByCompany(constraints));
        computerDao.delete(constraints);
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
