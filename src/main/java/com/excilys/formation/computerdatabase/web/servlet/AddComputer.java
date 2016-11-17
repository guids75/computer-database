package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.service.CompanyService;

/**
 * @author GUIDS
 *
 */
public class AddComputer extends HttpServlet {

  private static CompanyService companyService = CompanyService.getInstance(); //service of Company to manage them
  private static final Logger logger = LoggerFactory.getLogger(Test.class);
  private Page<Company> pages; //pages' attributes to manage them
  
  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/addComputer.jsp" ).forward( request, response );

  }


}
