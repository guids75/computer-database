package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.validation.servlet.ComputerValidator;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

public class EditComputer extends HttpServlet {

    private static final long serialVersionUID = 1565503698100849730L;
    @Autowired
    private ComputerService computerService; // service of Company to manage them
    @Autowired
    private CompanyService companyService; // service of Company to manage them
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);


    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public void init() throws ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());       
        this.companyService = (CompanyService) webApplicationContext.getBean(CompanyService.class);
        this.computerService = (ComputerService) webApplicationContext.getBean(ComputerService.class);
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
            try {
                computerService.update(ComputerDtoMapper.computerDtoToComputer(computer));
            } catch (NotImplementedMethodException exception) {
                slf4jLogger.error("update in computerService is not implemented yet", exception);
            }
            request.getRequestDispatcher("/dashboardSubmit").forward(request, response);
        }
    }

}
