package com.excilys.formation.computerdatabase.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.RequestParamMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputerController {

    @Autowired
    private ComputerService computerService; // service of Company to manage them
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);


    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }
    
    
    @PostMapping
    protected ModelAndView post(@RequestParam Map<String, String> parameters) throws Exception {

        ModelAndView model = new ModelAndView("redirect:dashboard");
        List<Long> computersId = RequestParamMapper.convertToComputersId(parameters);
        try {
            computerService.delete(new Constraints.ConstraintsBuilder().idList(computersId).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in computerService is not implemented yet", exception);
        }
        return model;
    }

    @GetMapping
    protected ModelAndView get(@RequestParam Map<String, String> parameters) throws Exception {
        return new ModelAndView("dashboard");
    }
    
}
