package com.excilys.formation.computerdatabase.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestParamMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.Constraints.ConstraintsBuilder;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    
    @Autowired
    private ComputerService computerService; // service of Computer to manage them

    
    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    protected ModelAndView get(@RequestParam Map<String, String> parameters) throws Exception {

        ModelAndView model = new ModelAndView("dashboard");
        ConstraintsBuilder constraints = RequestParamMapper.convertToConstraints(parameters);
        Page pages = new RequestParamMapper().convertToPage(parameters);
        if (parameters.get("search") != null) {
            model.addObject("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.search(constraints
                    .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build())));
            model.addObject("search", parameters.get("search"));
        } else {
            model.addObject("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(constraints
                    .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build())));
        }
        model.addObject("pages", pages);
        return model;
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
