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
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
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
    Computer computer = RequestMapper.convertToComputer(request); 
    computerServiceImpl.insert(computer);
    request.getRequestDispatcher( "/dashboard" ).forward( request, response );
  }


}
