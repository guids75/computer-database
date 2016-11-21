package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GUIDS
 *
 */
public class MainMenuSubmit extends HttpServlet {
  
  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    String entityChosen = request.getParameter("entityType");
    
    switch (entityChosen) {
    case ("companies"):
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/mainMenuCompanies.jsp" ).forward( request, response );
      break;
    case ("computers"):
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/mainMenuComputers.jsp" ).forward( request, response );
      break;
    default:
      break;
    }


  }

}
