package com.excilys.formation.computerdatabase.controller;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.mapper.RequestParamMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.constraints.Constraints.ConstraintsBuilder;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

@Controller
public class DashboardController {
    
    @Autowired
    private ComputerService computerService; // service of Computer to manage them

    
    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/dashboard")
    protected ModelAndView get(@RequestParam Map<String, String> parameters) throws Exception {
        System.out.println(1);
        ModelAndView model = new ModelAndView("dashboard");
        System.out.println(2);
        Locale locale = LocaleContextHolder.getLocale();
        ConstraintsBuilder constraints = RequestParamMapper.convertToConstraints(parameters);
        System.out.println(3);
        Page pages = new RequestParamMapper().convertToPage(parameters);
        System.out.println(5);
        if (parameters.get("search") != null) {
            model.addObject("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.search(constraints
                    .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build()),locale));
            model.addObject("search", parameters.get("search"));
        } else {
            model.addObject("listComputers", ComputerDtoMapper.computerListToComputerDtoList(computerService.list(constraints
                    .limit(pages.getNbElementsByPage()).offset((pages.getActualPage() - 1) * pages.getNbElementsByPage()).build()),locale));
        }
        System.out.println(6);
        model.addObject("pages", pages);
        System.out.println(4);
        return model;
    }
    
    @RequestMapping("/resources/js/internationalizationStrings.js")
    public ModelAndView strings() {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("cdb", locale);
        return new ModelAndView("internationalizationStrings", "keys", messages.getKeys());
    }
    
    @GetMapping(value = "/login")
    public String login(ModelMap model) {
        return "login";
    }
 
    @GetMapping(value = "/accessdenied")
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }
 
    @GetMapping(value = "/logout")
    public String logout(ModelMap model) {
        return "logout";
    }
    
}
