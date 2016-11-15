package com.excilys.formation.computerdatabase.servlet;

import javax.servlet.http.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Test extends HttpServlet{
  
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
  
  public void doGet( HttpServletRequest request, HttpServletResponse response )   throws ServletException, IOException {

    this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );

}

}
