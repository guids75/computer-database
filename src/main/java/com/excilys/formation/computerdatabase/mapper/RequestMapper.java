package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

public final class RequestMapper {

  private static final ComputerServiceImpl computerService = ComputerServiceImpl.INSTANCE;
  
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

  public static List<Long> convertToComputersId(HttpServletRequest request) {
    List<Long> computersId = new ArrayList<>();
    String selection = request.getParameter("selection");
    String[] deletedComputers = selection.split(",");
    if (deletedComputers.length > 0) {
      for (int i = 0; i < deletedComputers.length; i++) {
        computersId.add(Long.parseLong(deletedComputers[i]));
      }
    }
    return computersId;
  }
  
  public static Page convertToPage(HttpServletRequest request) {
    Page pages = new Page();
    //if there is an actual page, use it, otherwise use the first page
    if (request.getParameter("actualPage") != null) {
      pages.setActualPage(Integer.parseInt(request.getParameter("actualPage")));
    } else {
      pages.setActualPage(1);
    }
    //if there is a nbElementsByPage defined, use it, otherwise use 10
    if (request.getParameter("nbElementsByPage") != null) {
      pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElementsByPage")));
    } else {
      pages.setNbElementsByPage(10);
    }
    //get the right number of pages according to the search
    if (request.getParameter("search") != null) {
      pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search(request.getParameter("search")).build()));
    } else {
      pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search("").build()));
    }
    pages.calculateNbPages(pages.getNbElements());
    
    //when it's the last page
    if (pages.getActualPage() - 1 * pages.getNbElementsByPage() >= pages.getNbElements()) {
      pages.setActualPage(pages.getActualPage() - 1);
    }  
    
    return pages;
  }

}
