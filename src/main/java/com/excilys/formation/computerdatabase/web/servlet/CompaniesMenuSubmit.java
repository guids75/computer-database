package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.service.CompanyService;

public class CompaniesMenuSubmit extends HttpServlet {

  private static CompanyService companyService = CompanyService.getInstance();
  private static final Logger logger = LoggerFactory.getLogger(Test.class);

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

    String actionChosen = request.getParameter("companiesAction");

    request.setAttribute( "actionChosen", actionChosen );

    switch (actionChosen){
    case ("listCompanies"):
      int numberCompanies = companyService.getNumber();
      request.setAttribute( "numberCompanies", numberCompanies );
      request.setAttribute( "listCompanies", companyService.list(numberCompanies, 0));
      System.out.println(companyService.list(numberCompanies, 0));
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listCompanies.jsp" ).forward( request, response );
    break;
    default:
      break;
    }
    

  }

}
