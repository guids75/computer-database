package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

/**
 * @author GUIDS
 *
 */
public class DashboardSubmit extends HttpServlet {

    private static final long serialVersionUID = 3765045871388643647L;
    private static final ComputerService computerService = 
            ComputerServiceImpl.INSTANCE; // service of Computer to manage them

    public DashboardSubmit() {
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Page pages = RequestMapper.convertToPage(request);
        pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search("").build()));
        pages.calculateNbPages(pages.getNbElements());
        request.setAttribute("pages", pages);
        request.setAttribute( "listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                .limit(pages.getNbElementsByPage()).offset(0).build())));
        this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/dashboard.jsp" ).forward( request, response );
    }

}