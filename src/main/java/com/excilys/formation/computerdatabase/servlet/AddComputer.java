package com.excilys.formation.computerdatabase.servlet;

import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;
import com.excilys.formation.computerdatabase.validation.servlet.ComputerValidator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author GUIDS
 *
 */
public class AddComputer extends HttpServlet {

    private static final long serialVersionUID = -2391324266165934348L;
    private CompanyServiceImpl companyService; // service of Company to manage them
    private ComputerServiceImpl computerService; // service of Company to manage them
    

    public CompanyServiceImpl getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    public ComputerServiceImpl getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerServiceImpl computerService) {
        this.computerService = computerService;
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
        computerService.insert(ComputerDtoMapper.computerDtoToComputer(computer));
        request.getRequestDispatcher("/dashboardSubmit").forward(request, response);
        }
    }

}
