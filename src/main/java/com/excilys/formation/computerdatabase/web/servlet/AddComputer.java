package com.excilys.formation.computerdatabase.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

/**
 * @author GUIDS
 *
 */
public class AddComputer extends HttpServlet {

  private static CompanyServiceImpl companyServiceImpl = CompanyServiceImpl.getInstance(); //service of Company to manage them
  private static ComputerServiceImpl computerServiceImpl = ComputerServiceImpl.getInstance(); //service of Company to manage them
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd"); 
  private Page<Company> pages; //pages' attributes to manage them

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    request.setAttribute( "listCompanies", companyServiceImpl.list(companyServiceImpl.count(), 0));
    this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/addComputer.jsp" ).forward( request, response );

  }

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    if (request.getParameter("computerName") != null && request.getParameter("companyId") != null) { 
      LocalDate introduced = null;
      LocalDate discontinued = null;
      if (request.getParameter("introduced") != null) {
        //introduced = localDateMapper.convertToLocalDate(simpleDateFormat.parse(request.getParameter("introduced")));
      }
      Computer.ComputerBuilder computer = new Computer.ComputerBuilder(request.getParameter("computerName"))
          .company(companyServiceImpl.getCompany(Integer.parseInt(request.getParameter("companyId"))));
      if (introduced != null) {
        computer.introduced(LocalDate.parse(simpleDateFormat.format(request.getParameter("introduced"))));
      }
      if (discontinued != null) {
        computer.discontinued(LocalDate.parse(simpleDateFormat.format(request.getParameter("discontinued"))));
      }   
      computerServiceImpl.insert(computer.build());
      request.getRequestDispatcher( "/dashboard" ).forward( request, response );

    }
  }


}
