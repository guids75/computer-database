package com.excilys.formation.computerdatabase.ui;

import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    
    
    public static CompanyUi getCompanyUi() {
        return companyUi;
    }

    public static void setCompanyUi(CompanyUi companyUi) {
        Cli.companyUi = companyUi;
    }

    public static ComputerUi getComputerUi() {
        return computerUi;
    }

    public static void setComputerUi(ComputerUi computerUi) {
        Cli.computerUi = computerUi;
    }


    public static void main(String[] args) {
        
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("bean.xml"); 
        
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
