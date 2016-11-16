package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.service.ComputerService;

public class ComputersMenuSubmit extends HttpServlet {

  private ComputerService computerService = ComputerService.getInstance();
  private static final Logger logger = LoggerFactory.getLogger(Test.class);

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

    String actionChosen = request.getParameter("computersAction");

    request.setAttribute( "actionChosen", actionChosen );

    switch (actionChosen){
    case ("listComputers"):
      int numberComputers = computerService.getNumber();
    request.setAttribute( "numberComputers", numberComputers );
    request.setAttribute( "listComputers", computerService.list(numberComputers, 0));
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listComputers.jsp" ).forward( request, response );
    break;
    case ("addComputer"):
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/addComputer.jsp" ).forward( request, response );
    break;
    case ("updateComputer"):
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/updateComputer.jsp" ).forward( request, response );
    break;
    case ("deleteComputer"):
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/deleteComputer.jsp" ).forward( request, response );
    break;
    default:
      break;
    }
  }

}
