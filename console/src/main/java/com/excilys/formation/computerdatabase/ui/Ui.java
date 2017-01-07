package com.excilys.formation.computerdatabase.ui;

import java.util.Scanner;

/**
 * @author GUIDS
 *
 */
public interface Ui {

    Scanner scanner = new Scanner(System.in);

    /**
     * List of objects.
     * 
     */
    public void list();

}
