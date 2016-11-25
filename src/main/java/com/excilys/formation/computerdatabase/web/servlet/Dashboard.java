package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

/**
 * @author GUIDS
 *
 */
public class Dashboard extends HttpServlet {

  private static final ComputerServiceImpl computerService = 
      ComputerServiceImpl.INSTANCE; // service of Computer to manage them
  private Page pages; // pages' attributes to manage them

  public Dashboard() {
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Page pages = RequestMapper.convertToPage(request);
    if (request.getParameter("search") != null) {
      pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search(request.getParameter("search")).build()));
      request.setAttribute("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.search(new Constraints.ConstraintsBuilder()
          .search(request.getParameter("search")).limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build())));
      request.setAttribute("search", request.getParameter("search"));
    } else {
      pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search("").build()));
      request.setAttribute("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
          .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build())));
    }
    pages.calculateNbPages(pages.getNbElements());
    //when it's the last page
    if (pages.getActualPage() - 1 * pages.getNbElementsByPage() >= pages.getNbElements()) {
      pages.setActualPage(pages.getActualPage() - 1);
    }  
    request.setAttribute("pages", pages);
    this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
  }

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    Page pages = RequestMapper.convertToPage(request);
    pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search("").build()));
    pages.calculateNbPages(pages.getNbElements());
    request.setAttribute("pages", pages);
    request.setAttribute( "listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
        .limit(pages.getNbElementsByPage()).offset(0).build())));
    this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
}
  
}
