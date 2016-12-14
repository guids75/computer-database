package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

public class DeleteComputer extends HttpServlet {

    private static final long serialVersionUID = -3947839916923007223L;
    private ComputerServiceImpl computerService; // service of Company to manage them


    public ComputerServiceImpl getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerServiceImpl computerService) {
        this.computerService = computerService;
    }

    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Long> computersId = RequestMapper.convertToComputersId(request);
        computerService.delete(new Constraints.ConstraintsBuilder().idList(computersId).build());
        request.getRequestDispatcher("/dashboard").forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/dashboard").forward(request, response);
    }

}
