package com.excilys.formation.computerdatabase.controller;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestParamMapper;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {

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


    @PostMapping
    protected ModelAndView post(@Valid @ModelAttribute("computerDto") ComputerDto computerDto, BindingResult result, @RequestParam Map<String, String> parameters) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        ModelAndView model = new ModelAndView("redirect:dashboard");
        if (result.hasErrors()) {
            System.out.println(result.toString());
            model = new ModelAndView("addComputer");
            model.addObject("listCompanies", CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                    .limit(companyService.count()).offset(0).build())));
            return model;
        } else {
            try {
                computerService.insert(ComputerDtoMapper.computerDtoToComputer(computerDto,locale));
            } catch (NotImplementedMethodException exception) {
                slf4jLogger.error("insert in companyService is not implemented yet", exception);
            }
            model.addObject("listCompanies", CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                    .limit(companyService.count()).offset(0).build())));
            model.addObject("addSuccess", computerDto.getName());
        }
        return model;
    }

    @GetMapping
    protected ModelAndView get(@RequestParam Map<String, String> parameters) throws Exception {

        ModelAndView model = new ModelAndView("addComputer");
        model.getModel().put("computerDto", new ComputerDto());
        model.addObject("listCompanies", CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                .limit(companyService.count()).offset(0).build())));
        return model;
    }


}
