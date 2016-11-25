package com.excilys.formation.computerdatabase.web.servlet;

import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GUIDS
 *
 */
public class AddComputer extends HttpServlet {

  private static CompanyServiceImpl companyServiceImpl = 
      CompanyServiceImpl.INSTANCE; // service of Company to manage them
  private static ComputerServiceImpl computerServiceImpl = 
      ComputerServiceImpl.INSTANCE; // service of Company to manage them

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("listCompanies", CompanyDtoMapper.companyListToCompanyDtoList(companyServiceImpl.list(new Constraints.ConstraintsBuilder()
        .limit(companyServiceImpl.count()).offset(0).build())));
    this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ComputerDto computer = RequestMapper.convertToComputer(request);
    computerServiceImpl.insert(ComputerDtoMapper.computerDtoToComputer(computer));
    request.getRequestDispatcher("/dashboard").forward(request, response);
  }

}
