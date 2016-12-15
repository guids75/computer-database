package com.excilys.formation.computerdatabase.ui;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.excilys.formation.computerdatabase.persistence.company.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.ui.company.CompanyUi;
import com.excilys.formation.computerdatabase.ui.company.CompanyUiImpl;
import com.excilys.formation.computerdatabase.ui.computer.ComputerUi;
import com.excilys.formation.computerdatabase.ui.computer.ComputerUiImpl;

/**
 * @author GUIDS
 *
 */
public class Cli {

    public static Scanner in = new Scanner(System.in);
    private static CompanyUi companyUi;
    private static ComputerUi computerUi;


    public static void main(String[] args) {
        
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        companyUi = (CompanyUiImpl) appContext.getBean(CompanyUi.class);
        computerUi = (ComputerUiImpl) appContext.getBean(ComputerUi.class);
        
        while (true) {

            // main menu
            System.out.println("Options available :\n" + "- list computers\n" + "- list companies\n"
                    + "- computer details\n" + "- insert computer\n" + "- update computer\n" + "- delete computer\n");

            String action = in.nextLine();

            switch (action) {
            case "list computers":
                computerUi.list();
                break;
            case "list companies":
                companyUi.list();
                break;
            case "delete company":
                companyUi.delete();
                break;
            case "computer details":
                computerUi.showComputerDetails();
                break;
            case "insert computer":
                computerUi.insert();
                break;
            case "update computer":
                computerUi.update();
                break;
            case "delete computer":
                computerUi.delete();
                break;
            default:
                break;
            }
        }

    }

}
