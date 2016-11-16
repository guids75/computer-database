package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainMenu extends HttpServlet {

  private static final Logger logger = LoggerFactory.getLogger(Test.class);

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

    String paramAuteur = request.getParameter( "auteur" );

    String message = "Transmission de variables : OK ! " + paramAuteur;

    request.setAttribute( "test", message );


    this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/mainMenu.jsp" ).forward( request, response );

  }

}
