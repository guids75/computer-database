package com.excilys.formation.computerdatabase.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestMapper;
import com.excilys.formation.computerdatabase.mapper.RequestParamMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import com.excilys.formation.computerdatabase.validation.servlet.ComputerValidator;

@Controller
@RequestMapping("/dashboardSubmit")
public class DashboardSubmitController {
    
    @Autowired
    private ComputerService computerService; // service of Computer to manage them
    
    
    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }
  
    
    @PostMapping
    protected ModelAndView post(@RequestParam Map<String, String> parameters) throws Exception {

        ModelAndView model = new ModelAndView("dashboard");
        Page pages = new RequestParamMapper().convertToPage(parameters);
        pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search("").build()));
        pages.calculateNbPages(pages.getNbElements());
        model.addObject("pages", pages);
        model.addObject( "listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                .limit(pages.getNbElementsByPage()).offset(0).build())));
        return model;
    }
    
}
