package com.excilys.formation.computerdatabase.servlet;

import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import com.excilys.formation.computerdatabase.validation.servlet.ComputerValidator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author GUIDS
 *
 */
public class AddComputer extends HttpServlet {

    private static final long serialVersionUID = -2391324266165934348L;
    @Autowired
    private CompanyService companyService; // service of Company to manage them
    @Autowired
    private ComputerService computerService; // service of Company to manage them
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);


    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }


    @Override
    public void init() throws ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        this.companyService = (CompanyService) webApplicationContext.getBean(CompanyService.class);
        this.computerService = (ComputerService) webApplicationContext.getBean(ComputerService.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listCompanies", CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                .limit(companyService.count()).offset(0).build())));
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerDto computer = RequestMapper.convertToComputer(request);
        if (!ComputerValidator.validate(computer).isEmpty()) {
            doGet(request, response);
        } else {
            try {
                computerService.insert(ComputerDtoMapper.computerDtoToComputer(computer));
            } catch (NotImplementedMethodException exception) {
                slf4jLogger.error("insert in companyService is not implemented yet", exception);
            }
            request.getRequestDispatcher("/dashboardSubmit").forward(request, response);
        }
    }

}
