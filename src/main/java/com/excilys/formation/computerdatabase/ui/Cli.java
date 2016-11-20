package com.excilys.formation.computerdatabase.ui;

import java.util.Scanner;

import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.exception.ConnectionException;

import org.slf4j.Logger;

/**
 * @author GUIDS
 *
 */
public class Cli {
  
  public static void main(String[] args) {

    ComputerUi computerUi = new ComputerUi();
    CompanyUi companyUi = new CompanyUi();

    while (true) {

      //main menu
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
          computerUi.list();
          break;
        case "list companies":
          companyUi.list();
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
        default :
          break;
      }
    }

  }

}
