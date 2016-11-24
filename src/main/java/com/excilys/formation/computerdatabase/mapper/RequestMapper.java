package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.pagination.Page;

public final class RequestMapper {

  private RequestMapper() {
  }

  public static ComputerDto convertToComputer(HttpServletRequest request) {
    if (request.getParameter("computerName") != null && request.getParameter("companyId") != null) {
      ComputerDto computer = new ComputerDto();
      computer.setName(request.getParameter("computerName"));
      computer.setCompanyId(Long.parseLong(request.getParameter("companyId")));
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
        computer.setId(Long.parseLong(request.getParameter("id")));
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
        computer.setId(Long.parseLong(deletedComputers[i]));
        computers.add(computer);
      }
    }
    return computers;
  }

  public static Page convertToPage(HttpServletRequest request) {
    Page pages = new Page();
    if (request.getParameter("page") != null) {
      pages.setActualPage(Integer.parseInt(request.getParameter("page")));
    } else {
      pages.setActualPage(1);
    }
    if (request.getParameter("nbElementsByPage") != null) {
      pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElementsByPage")));
    } else {
      pages.setNbElementsByPage(10);
    }
    return pages;
  }

}
