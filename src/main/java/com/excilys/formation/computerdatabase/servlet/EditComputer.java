package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;
import com.excilys.formation.computerdatabase.dto.ComputerDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

public class EditComputer extends HttpServlet {

  private static final long serialVersionUID = 1565503698100849730L;
  private static ComputerServiceImpl computerServiceImpl = 
      ComputerServiceImpl.INSTANCE; // service of Company to manage them
  private static CompanyServiceImpl companyServiceImpl = 
      CompanyServiceImpl.INSTANCE; // service of Company to manage them

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ComputerDto computerDto = RequestMapper.convertToComputer(request);
    request.setAttribute("listCompanies", companyServiceImpl.list(new Constraints.ConstraintsBuilder().limit(companyServiceImpl.count()).offset(0).build()));
    request.setAttribute("computer", computerDto);
    request.getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ComputerDto computer = RequestMapper.convertToComputer(request);
    computerServiceImpl.update(ComputerDtoMapper.computerDtoToComputer(computer));
    request.getRequestDispatcher("/dashboard").forward(request, response);
  }

}
