package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

/**
 * @author GUIDS
 *
 */
public class Dashboard extends HttpServlet {

  private static final ComputerServiceImpl computerService = ComputerServiceImpl.getInstance(); //service of Computer to manage them
  private Page<Computer> pages; //pages' attributes to manage them

  public Dashboard() {
  }

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    if (request.getParameter("page") != null) {  
      pages.setActualPage(Integer.parseInt(request.getParameter("page")));
      if (request.getParameter("nbElements") != null) {
        pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElements")));
        pages.calculateNbPages(pages.getNbElements());
      }

      if (pages.getActualPage() - 1 * pages.getNbElementsByPage() >= pages.getNbElements()) {
        pages.setActualPage(pages.getActualPage() - 1);
      }
      request.setAttribute("pages", pages);
      if (request.getParameter("search") != null) {
        request.setAttribute( "listComputers", computerService.search(request.getParameter("search"), pages.getNbElementsByPage(), 
            (pages.getActualPage() - 1) * pages.getNbElementsByPage()));
        request.setAttribute("search", request.getParameter("search"));
      } else {
        request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), (pages.getActualPage() - 1) * pages.getNbElementsByPage()));
      }
    } else {
      pages = new Page<>(computerService.count());
      pages.setNbElementsByPage(10);
      request.setAttribute("pages", pages);
      if (request.getParameter("search") != null) {
        request.setAttribute( "listComputers", computerService.search(request.getParameter("search"), pages.getNbElementsByPage(), 0));
        request.setAttribute("search", request.getParameter("search"));
      } else {
        request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), 0));
      }

    }
    this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
  }

}
