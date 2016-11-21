package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.pagination.Page;

public class RequestMapper {

  private static CompanyServiceImpl companyServiceImpl = CompanyServiceImpl.getInstance(); //service of Company to manage them

  public static Computer convertToComputer(HttpServletRequest request) {
    if (request.getParameter("computerName") != null && request.getParameter("companyId") != null) { 
      Computer.ComputerBuilder computer = new Computer.ComputerBuilder(request.getParameter("computerName"))
          .company(companyServiceImpl.getCompany(Integer.parseInt(request.getParameter("companyId"))));
      if (request.getParameter("introduced") != "") {
        computer.introduced(LocalDate.parse(request.getParameter("introduced")));
      }
      if (request.getParameter("discontinued") != "") {
        computer.discontinued(LocalDate.parse(request.getParameter("discontinued")));
      }  
      return computer.build();
    }
    return null;
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
