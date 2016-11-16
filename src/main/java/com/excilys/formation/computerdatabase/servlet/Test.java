package com.excilys.formation.computerdatabase.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {

  private static final Logger logger = LoggerFactory.getLogger(Test.class);

  /*public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    response.setContentType("text/html");
    response.setCharacterEncoding( "UTF-8" );
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\" />");
    out.println("<title>Test</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p>Ceci est une page générée depuis une servlet.</p>");
    out.println("</body>");
    out.println("</html>");

    String name = "yooo";
    logger.info("Hello hello");

    logger.debug("In bar my name is {}.", name);
    logger.trace("here");
  }*/

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    String message = "Transmission de variables : OK !";

    request.setAttribute( "test", message );

    this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );

  }

}
