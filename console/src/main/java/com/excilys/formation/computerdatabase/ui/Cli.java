package com.excilys.formation.computerdatabase.ui;

import java.util.Scanner;

import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.formation.computerdatabase.ui.company.CompanyUi;
import com.excilys.formation.computerdatabase.ui.computer.ComputerUi;
import com.excilys.formation.computerdatabase.ui.user.UserUi;

/**
 * @author GUIDS
 *
 */
public class Cli {

    public static Scanner in = new Scanner(System.in);
    private static CompanyUi companyUi;
    private static ComputerUi computerUi;
    private static UserUi userUi;

    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context-console.xml");
        companyUi = appContext.getBean(CompanyUi.class);
        computerUi = appContext.getBean(ComputerUi.class);
        userUi = appContext.getBean(UserUi.class);
        
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        while (true) {

            // main menu
            System.out.println("Options available :\n" + "- list computers\n" + "- list companies\n"
                    + "- computer details\n" + "- insert computer\n" + "- update computer\n" + "- delete computer\n" + "- insert user\n" + "- list users\n");

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
            case "insert user":
                userUi.insert();
                break;
            case "list users":
                userUi.list();
                break;
            default:
                break;
            }
        }

    }

}