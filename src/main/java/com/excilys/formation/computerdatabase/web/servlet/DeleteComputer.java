package com.excilys.formation.computerdatabase.web.servlet;

import java.io.IOException;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

public class DeleteComputer extends HttpServlet {

  private static ComputerServiceImpl computerServiceImpl = ComputerServiceImpl.getInstance(); //service of Company to manage them

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    List<ComputerDto> computers = RequestMapper.convertToComputers(request); 
    for (ComputerDto computer : computers) {
      computerServiceImpl.delete(computer.getId());
    }
    request.getRequestDispatcher( "/dashboard" ).forward( request, response );
  }
 
  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    request.getRequestDispatcher( "/dashboard" ).forward( request, response );
  }

}
