package com.excilys.formation.computerdatabase.ui;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Page;

import java.util.Scanner;



public class Cli {

  public static void main(String[] args) {

    ComputerUi cpUI = new ComputerUi();
    CompanyUi cnUI = new CompanyUi();
    Page<Computer> pagesComputer = new Page<>();
    Page<Company> pagesCompany = new Page<>();

    while (true){

      System.out.println("Options available :\n"
          + "- list computers\n"
          + "- list companies\n"
          + "- computer details\n"
          + "- insert computer\n"
          + "- update computer\n"
          + "- delete computer\n"
          );

      Scanner in = new Scanner(System.in);
      String action = in.nextLine();

      switch (action) {
      case "list computers":
        cpUI.list(pagesComputer);
        break;
      case "list companies":
        cnUI.list(pagesCompany);
        break;
      case "computer details":
        cpUI.showComputerDetails();
        break;
      case "insert computer":
        cpUI.insert(pagesComputer);
        break;
      case "update computer":
        cpUI.update(pagesComputer);
        break;
      case "delete computer":
        cpUI.delete(pagesComputer);
        break;
      }
    }

  }

}
