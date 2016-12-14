package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.ComputerDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.validation.servlet.ComputerValidator;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

public class EditComputer extends HttpServlet {

    private static final long serialVersionUID = 1565503698100849730L;
    private ComputerServiceImpl computerService; // service of Company to manage them
    private CompanyServiceImpl companyService; // service of Company to manage them
    
    public ComputerServiceImpl getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerServiceImpl computerService) {
        this.computerService = computerService;
    }

    public CompanyServiceImpl getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }
    

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerDto computerDto = RequestMapper.convertToComputer(request);
        request.setAttribute("listCompanies", companyService.list(new Constraints.ConstraintsBuilder().limit(companyService.count()).offset(0).build()));
        request.setAttribute("computer", computerDto);
        request.getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerDto computer = RequestMapper.convertToComputer(request);
        List<String> errors = ComputerValidator.validate(computer);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors.get(0));
            doGet(request, response);
        } else {
        computerService.update(ComputerDtoMapper.computerDtoToComputer(computer));
        request.getRequestDispatcher("/dashboardSubmit").forward(request, response);
        }
    }

}
