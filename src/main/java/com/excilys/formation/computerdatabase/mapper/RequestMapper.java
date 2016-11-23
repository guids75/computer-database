package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.computerdatabase.dto.ComputerDto;

public final class RequestMapper {

  public static ComputerDto convertToComputer(HttpServletRequest request) {
    if (request.getParameter("computerName") != null && request.getParameter("companyId") != null) { 
      ComputerDto computer = new ComputerDto();
      computer.setName(request.getParameter("computerName"));
      computer.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
      if (request.getParameter("introduced") != "") {
        computer.setIntroduced(request.getParameter("introduced"));
      } else {
        computer.setIntroduced(null);
      }
      if (request.getParameter("discontinued") != "") {
        computer.setDiscontinued(request.getParameter("discontinued"));
      } else {
        computer.setDiscontinued(null);
      }
      if (request.getParameter("id") != null) {
        computer.setId(Integer.parseInt(request.getParameter("id")));
      }
      return computer;
    }
    return null;
  }

  public static List<ComputerDto> convertToComputers(HttpServletRequest request) {
    List<ComputerDto> computers = new ArrayList<>();
    String selection = request.getParameter("selection");
    String[] deletedComputers = selection.split(",");
    if (deletedComputers.length > 0) {
      for (int i = 0; i < deletedComputers.length; i++) {
        ComputerDto computer = new ComputerDto();
        computer.setId(Integer.parseInt(deletedComputers[i]));
        computers.add(computer);
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
