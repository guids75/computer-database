package com.excilys.formation.computerdatabase.service.computer;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.constraints.Constraints;
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
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerDao computerDao; // dao for Computer to manage the computers

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
        computerDao.delete(constraints);
    }

    @Override
    public List<Computer> list(Constraints constraints) {
        return computerDao.list(constraints);
    }

    @Override
    public Computer showComputerDetails(Long computerId) {
        return computerDao.showComputerDetails(computerId);
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
