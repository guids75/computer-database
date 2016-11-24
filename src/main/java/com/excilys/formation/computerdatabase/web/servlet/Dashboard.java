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
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.pagination.Page;
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
    int nbElements;
    Page pages = RequestMapper.convertToPage(request);
    if (request.getParameter("search") != null) {
      nbElements = computerService.count(request.getParameter("search"));
      request.setAttribute("listComputers", computerService.search(request.getParameter("search"),
          pages.getNbElementsByPage(), (pages.getActualPage() - 1) * pages.getNbElementsByPage()));
      request.setAttribute("search", request.getParameter("search"));
    } else {
      nbElements = computerService.count(request.getParameter(""));
      request.setAttribute("listComputers", computerService.list(pages.getNbElementsByPage(),
          (pages.getActualPage() - 1) * pages.getNbElementsByPage()));
    }
    
    pages.calculateNbPages(nbElements);
    //when it's the last page
    if (pages.getActualPage() - 1 * pages.getNbElementsByPage() >= pages.getNbElements()) {
      pages.setActualPage(pages.getActualPage() - 1);
    }
    
    request.setAttribute("pages", pages);
    
    this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
  }

}
