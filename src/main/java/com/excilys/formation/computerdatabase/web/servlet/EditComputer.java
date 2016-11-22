package com.excilys.formation.computerdatabase.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

public class EditComputer extends HttpServlet {

  private static ComputerServiceImpl computerServiceImpl = ComputerServiceImpl.getInstance(); //service of Company to manage them
  private static CompanyServiceImpl companyServiceImpl = CompanyServiceImpl.getInstance(); //service of Company to manage them
  private int computerId = -1;
  
  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    computerId = Integer.parseInt(request.getParameter("computerId"));
    request.setAttribute( "listCompanies", companyServiceImpl.list(companyServiceImpl.count(), 0));
    request.setAttribute("computerId", computerId);
    request.getRequestDispatcher( "/WEB-INF/jsp/editComputer.jsp" ).forward( request, response );
  }
  
  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    Computer computer = RequestMapper.convertToComputer(request); 
    computerServiceImpl.update(computer);
    request.getRequestDispatcher( "/dashboard" ).forward( request, response );
  }
  
}
