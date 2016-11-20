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
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.service.ComputerService;

/**
 * @author GUIDS
 *
 */
public class ComputersMenuSubmit extends HttpServlet {

  private static final ComputerService computerService = ComputerService.getInstance(); //service of Computer to manage them
  private static final Logger logger = LoggerFactory.getLogger(Test.class);
  private Page<Computer> pages; //pages' attributes to manage them

  public ComputersMenuSubmit() throws ConnectionException {
    pages = new Page<>(computerService.count());
    pages.setNbElementsByPage(10);
  }

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    /*if (request.getParameter("computersAction") != null) {  
      String actionChosen = request.getParameter("computersAction");*/
    request.setAttribute("pages", pages);

    /*switch (actionChosen) {
      case ("listComputers"):*/
    try {
      request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), 0));
    } catch (ConnectionException exception) {
      exception.printStackTrace();
    }
    this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listComputers.jsp" ).forward( request, response );
    /*break;
      default:
        break;
      }
    }*/

  }

  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    
    if (request.getParameter("page") != null) {  
      pages.setActualPage(Integer.parseInt(request.getParameter("page")));
      if (request.getParameter("nbElements") != null) {
        pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElements")));
        pages.calculateNbPages(pages.getNbElements());
      }

      try {
        request.setAttribute("pages", pages);
        if (pages.getActualPage()-1 * pages.getNbElementsByPage() < pages.getNbElements()){
          request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), (pages.getActualPage()-1) * pages.getNbElementsByPage()));
        } else {
          pages.setActualPage(pages.getActualPage()-1);
          request.setAttribute( "listComputers", computerService.list(pages.getNbElementsByPage(), (pages.getActualPage()-1) * pages.getNbElementsByPage()));
        }

        request.setAttribute("numberPages", pages.getNbPages());
      } catch (ConnectionException exception) {
        exception.printStackTrace();
      }
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listComputers.jsp" ).forward( request, response );
    } else {
      doPost(request, response);
    }

  }

}
