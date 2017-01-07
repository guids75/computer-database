package com.excilys.formation.computerdatabase.ui.computer;

import com.excilys.formation.computerdatabase.ui.Ui;

public interface ComputerUi extends Ui {

    /**
     * Show all the computer's attributes.
     * 
     */
    public void showComputerDetails();

    /**
     * Insert a computer in the service.
     * 
     */
    public void insert();

    /**
     * Update a computer.
     * 
     */
    public void update();

    /**
     * Delete a computer from the service.
     * 
     */
    public void delete();

}
