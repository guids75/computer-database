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
@RequestMapping("/editComputer")
public class EditComputerController {

    @Autowired
    private ComputerService computerService; // service of Company to manage them
    @Autowired
    private CompanyService companyService; // service of Company to manage them
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);


    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    protected ModelAndView get(@RequestParam Map<String, String> parameters) throws Exception {

        ModelAndView model = new ModelAndView("editComputer");
        model.getModel().put("computerDto", new ComputerDto());
        ComputerDto computerDto = RequestParamMapper.convertToComputer(parameters);
        model.addObject("listCompanies", companyService.list(new Constraints.ConstraintsBuilder().limit(companyService.count()).offset(0).build()));
        model.addObject("computer", computerDto);
        return model;
    }

    @PostMapping
    protected ModelAndView post(@Valid @ModelAttribute("computerDto") ComputerDto computerDto, BindingResult result, @RequestParam Map<String, String> parameters) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        ModelAndView model = new ModelAndView("redirect:dashboard");
        if (result.hasErrors()) {
            model = new ModelAndView("editComputer");
            model.addObject("listCompanies", CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                    .limit(companyService.count()).offset(0).build())));
            return model;
        } else {
            try {
                computerService.update(ComputerDtoMapper.computerDtoToComputer(computerDto, locale));
                model.addObject("editSuccess", computerDto.getName());
            } catch (NotImplementedMethodException exception) {
                slf4jLogger.error("update in computerService is not implemented yet", exception);
            }
        }
        return model;
    }

}
