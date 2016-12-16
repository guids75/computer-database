package com.excilys.formation.computerdatabase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

public class DeleteComputer extends HttpServlet {

    private static final long serialVersionUID = -3947839916923007223L;
    @Autowired
    private ComputerService computerService; // service of Company to manage them
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);


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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Long> computersId = RequestMapper.convertToComputersId(request);
        try {
            computerService.delete(new Constraints.ConstraintsBuilder().idList(computersId).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in computerService is not implemented yet", exception);
        }
        request.getRequestDispatcher("/dashboardSubmit").forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/dashboard").forward(request, response);
    }

}
