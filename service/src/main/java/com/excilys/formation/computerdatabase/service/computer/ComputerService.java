package com.excilys.formation.computerdatabase.service.computer;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.service.Service;

/**
 * @author GUIDS
 *
 * @param <T>
 *            : Computer
 */
public interface ComputerService extends Service<Computer> {

    /**
     * Show all the attributes of the specified computer.
     * 
     * @param computerId
     *            : the id of the computer
     */
    public void showComputerDetails(Long computerId);

    /**
     * @return the number of computers
     */
    public int count(Constraints constraints);

    public List<Computer> search(Constraints constraints);

}
