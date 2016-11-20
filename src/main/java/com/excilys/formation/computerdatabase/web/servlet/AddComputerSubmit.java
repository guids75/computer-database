package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import java.text.ParseException;
import com.excilys.formation.computerdatabase.mapper.LocalDateMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

import java.time.LocalDate;
/**
 * @author GUIDS
 *
 */
public class AddComputerSubmit extends HttpServlet {

  private static final ComputerServiceImpl computerService = ComputerServiceImpl.getInstance();
  private static final CompanyServiceImpl companyService = CompanyServiceImpl.getInstance(); //service of Company to manage them
  private static final LocalDateMapper localDateMapper = LocalDateMapper.getInstance(); //utility to manage the dates
  private static final Logger logger = LoggerFactory.getLogger(Test.class);
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd"); 
  private Page<Company> pages; //pages' attributes to manage them

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    if (request.getParameter("computerName") != null && request.getParameter("companyId") != null) { 
      LocalDate introduced = null;
      LocalDate discontinued = null;
      if (request.getParameter("introduced") != null) {
        //introduced = localDateMapper.convertToLocalDate(simpleDateFormat.parse(request.getParameter("introduced")));
      }
      try {
        Computer.ComputerBuilder computer = new Computer.ComputerBuilder(computerService.count()+1, 
            request.getParameter("computerName"), companyService.getCompany(Integer.parseInt(request.getParameter("companyId"))));
        if (introduced != null) {
          computer.introduced(localDateMapper.convertToLocalDate(simpleDateFormat.parse(request.getParameter("introduced"))));
        }
        if (discontinued != null) {
          computer.discontinued(localDateMapper.convertToLocalDate(simpleDateFormat.parse(request.getParameter("discontinued"))));
        }
        computerService.insert(computer.build());
        request.setAttribute( "numberCompanies", pages.getNbElements() );
        request.setAttribute( "listCompanies", companyService.list(pages.getNbElementsByPage(), 0));
        request.setAttribute("numberPages", pages.getNbPages());
      } catch (ConnectionException | ParseException exception) {
        exception.printStackTrace();
      }
      this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/listComputers.jsp" ).forward( request, response );

    }
  }
}
