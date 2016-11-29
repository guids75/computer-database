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

/**
 * @author GUIDS
 *
 */
public class AddComputer extends HttpServlet {

    private static final long serialVersionUID = -2391324266165934348L;
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
        if (!ComputerValidator.validate(computer).isEmpty()) {
            doGet(request, response);
        } else {
        computerServiceImpl.insert(ComputerDtoMapper.computerDtoToComputer(computer));
        request.getRequestDispatcher("/dashboardSubmit").forward(request, response);
        }
    }

}
