package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;

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

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints.ConstraintsBuilder;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

/**
 * @author GUIDS
 *
 */
public class Dashboard extends HttpServlet {

    private static final long serialVersionUID = 3765045871388643647L;
    @Autowired
    private ComputerService computerService; // service of Computer to manage them

    
    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    
    @Override
    public void init() throws ServletException {
       WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
       this.computerService = (ComputerService) webApplicationContext.getBean(ComputerService.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConstraintsBuilder constraints = RequestMapper.convertToConstraints(request);
        Page pages = new RequestMapper().convertToPage(request);
        if (request.getParameter("search") != null) {
            request.setAttribute("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.search(constraints
                    .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build())));
            request.setAttribute("search", request.getParameter("search"));
        } else {
            request.setAttribute("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(constraints
                    .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build())));
        }
        request.setAttribute("pages", pages);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }

}
