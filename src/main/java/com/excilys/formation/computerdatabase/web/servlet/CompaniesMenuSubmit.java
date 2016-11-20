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
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

/**
 * @author GUIDS
 *
 */
public class CompaniesMenuSubmit extends HttpServlet {

  private static CompanyServiceImpl companyService = CompanyServiceImpl.getInstance(); //service of Company to manage them
  private static final Logger logger = LoggerFactory.getLogger(Test.class);
  private Page<Company> pages; //pages' attributes to manage them

  public CompaniesMenuSubmit() throws ConnectionException {
    pages = new Page<>(companyService.count());
    pages.setActualPage(1);
    pages.setNbElementsByPage(10);
  }
  
  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    if (request.getParameter("companiesAction") != null) {  
      String actionChosen = request.getParameter("companiesAction");

      switch (actionChosen) {
      case ("listCompanies"):
        try {
          request.setAttribute( "numberCompanies", pages.getNbElements() );
          request.setAttribute( "listCompanies", companyService.list(pages.getNbElementsByPage(), 0));
          request.setAttribute("numberPages", pages.getNbPages());
        } catch (ConnectionException exception) {
          exception.printStackTrace();
        }
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listCompanies.jsp" ).forward( request, response );
      break;
      default:
        break;
      }
    }

  }

  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    if (request.getParameter("page") != null) {  
      pages.setActualPage(Integer.parseInt(request.getParameter("page")));
      if (request.getParameter("nbElements") != null) {
        pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElements")));
        pages.calculateNbPages(pages.getNbElements());
      }

      try {
        request.setAttribute( "numberCompanies", pages.getNbElements() );
        request.setAttribute("actualPage", pages.getActualPage());
        request.setAttribute("hasNext", pages.hasNext());
        request.setAttribute("hasPrev", pages.hasPrev());
        if (pages.getActualPage()-1 * pages.getNbElementsByPage() < pages.getNbElements()){
            request.setAttribute( "listCompanies", companyService.list(pages.getNbElementsByPage(), (pages.getActualPage()-1) * pages.getNbElementsByPage()));
        } else {
          pages.setActualPage(pages.getActualPage()-1);
          request.setAttribute( "listCompanies", companyService.list(pages.getNbElementsByPage(), (pages.getActualPage()-1) * pages.getNbElementsByPage()));
        }
        
        request.setAttribute("numberPages", pages.getNbPages());
      } catch (ConnectionException exception) {
        exception.printStackTrace();
      }
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listCompanies.jsp" ).forward( request, response );
    }

  }

}
