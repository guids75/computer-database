package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import java.time.format.DateTimeFormatter;

public class RequestMapper {

  private static CompanyServiceImpl companyServiceImpl = CompanyServiceImpl.getInstance(); //service of Company to manage them
  private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static Computer convertToComputer(HttpServletRequest request) {
    if (request.getParameter("computerName") != null && request.getParameter("companyId") != null) { 
      Computer.ComputerBuilder computer = new Computer.ComputerBuilder(request.getParameter("computerName"))
          .company(companyServiceImpl.getCompany(Integer.parseInt(request.getParameter("companyId"))));
      if (request.getParameter("introduced") != "") {
        computer.introduced(LocalDate.parse(request.getParameter("introduced"), dateTimeFormatter));
      }
      if (request.getParameter("discontinued") != "") {
        computer.discontinued(LocalDate.parse(request.getParameter("discontinued"), dateTimeFormatter));
      }  
      return computer.build();
    }
    return null;
  }

  public static List<Computer> convertToComputers(HttpServletRequest request) {
    List<Computer> computers = new ArrayList<>();
    String selection = request.getParameter("selection");
    String[] deletedComputers = selection.split(",");
    if (deletedComputers.length > 0) {
      for (int i=0; i<deletedComputers.length;i++) {
        computers.add(new Computer.ComputerBuilder("")
            .id(Integer.parseInt(deletedComputers[i])).build());
      }
    }
    return computers;
  }
    

  /*public static Page convertToPage(HttpServletRequest request) {
    if (request.getParameter("page") != null) {  
      pages.setActualPage(Integer.parseInt(request.getParameter("page")));
      if (request.getParameter("nbElements") != null) {
        pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElements")));
        pages.calculateNbPages(pages.getNbElements());
      }
    return null;
  }*/

}
