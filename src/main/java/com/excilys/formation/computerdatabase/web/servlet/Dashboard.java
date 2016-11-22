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
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    pages = new Page<>(computerService.count());
    pages.setNbElementsByPage(10);
    request.setAttribute("pages", pages);
    request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), 0));
    this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
  }

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    
    if (request.getParameter("page") != null) {  
      pages.setActualPage(Integer.parseInt(request.getParameter("page")));
      if (request.getParameter("nbElements") != null) {
        pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElements")));
        pages.calculateNbPages(pages.getNbElements());
      }

      request.setAttribute("pages", pages);
      if (pages.getActualPage()-1 * pages.getNbElementsByPage() < pages.getNbElements()){
        request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), (pages.getActualPage()-1) * pages.getNbElementsByPage()));
      } else {
        pages.setActualPage(pages.getActualPage()-1);
        request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), (pages.getActualPage()-1) * pages.getNbElementsByPage()));
      }

      request.setAttribute("numberPages", pages.getNbPages());
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
    } else {
      doPost(request, response);
    }

  }

}
