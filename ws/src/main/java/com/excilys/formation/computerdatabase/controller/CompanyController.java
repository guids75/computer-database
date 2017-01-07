package com.excilys.formation.computerdatabase.controller;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CompanyController implements Controller {

    @Autowired
    private CompanyService companyService; // service of Computer to manage them

    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/companies/{page}/{nbByPage}")
    public ResponseEntity<List<CompanyDto>> listCompanies(@PathVariable String page, @PathVariable String nbByPage) {
        List<CompanyDto> companies = CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                .limit(Integer.parseInt(nbByPage)).offset(Integer.parseInt(nbByPage)*(Integer.parseInt(page) - 1)).build()));
        return new ResponseEntity<List<CompanyDto>>(companies, HttpStatus.OK);
    }

    @DeleteMapping(value = "/companies/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            companyService.delete(new Constraints.ConstraintsBuilder().idCompany(id).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in CompanyController is not implemented yet", exception);
        }
        return responseEntity("Company was successfully deleted");
    }



}
