package com.excilys.formation.computerdatabase.controller;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.excilys.formation.computerdatabase.service.computer.ComputerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
public class ComputerController implements Controller{

    @Autowired
    private ComputerService computerService; // service of Computer to manage them

    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);

    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping(value = "/computers/{page}/{nbByPage}")
    public ResponseEntity<List<ComputerDto>> listComputers(@PathVariable String page, @PathVariable String nbByPage) {
        Locale locale = LocaleContextHolder.getLocale();
        List<ComputerDto> computers = ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                .limit(Integer.parseInt(nbByPage)).offset(Integer.parseInt(nbByPage)*(Integer.parseInt(page) - 1)).build()), locale);
        return responseEntityList(computers);
    }

    @GetMapping(value = "/computers/number")
    public ResponseEntity<Integer> countComputers() {
        Locale locale = LocaleContextHolder.getLocale();
        return responseEntity(computerService.count(new Constraints.ConstraintsBuilder().build()));
    }

    @GetMapping(value = "/computers/number/{search}")
    public ResponseEntity<Integer> countComputers(@PathVariable String search) {
        Locale locale = LocaleContextHolder.getLocale();
        return responseEntity(computerService.count(new Constraints.ConstraintsBuilder().search(search).build()));
    }

    @GetMapping(value = "/computers/search/{search}/{page}")
    public ResponseEntity<List<ComputerDto>> searchComputers(@PathVariable String search, @PathVariable String page) {
        Locale locale = LocaleContextHolder.getLocale();
        List<ComputerDto> computers = ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                .limit(10).offset(10*(Integer.parseInt(page) - 1)).search(search).build()), locale);
        return responseEntityList(computers);
    }

    @GetMapping(value = "/computers/show/{id}")
    public ResponseEntity<ComputerDto> showComputerDetails(@PathVariable String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return responseEntity(ComputerDtoMapper.computerToComputerDto(computerService.showComputerDetails(Long.parseLong(id)), locale));
    }

    @PostMapping(value = "/computers/add")
    public ResponseEntity<ComputerDto> addComputer(@RequestBody ComputerDto computerDto) {
        Locale locale = LocaleContextHolder.getLocale();
        Long id = -1L;
        try {
            id = computerService.insert(ComputerDtoMapper.computerDtoToComputer(computerDto,locale));
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("add in ComputerController is not implemented yet", exception);
        }
        return responseEntity(computerDto);
    }

    @PutMapping(value = "/computers/edit")
    public ResponseEntity<ComputerDto> editComputer(@RequestBody ComputerDto computerDto) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            computerService.update(ComputerDtoMapper.computerDtoToComputer(computerDto, locale));
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("edit in ComputerController is not implemented yet", exception);
        }
        return responseEntity(computerDto);
    }

    @DeleteMapping(value = "/computers/delete/{id}")
    public ResponseEntity<String> deleteComputer(@PathVariable String[] ids) {
        Locale locale = LocaleContextHolder.getLocale();
        List<Long> idArray = new ArrayList<>();
        for(String s : ids) idArray.add(Long.valueOf(s));
        try {
            computerService.delete(new Constraints.ConstraintsBuilder().idList(idArray).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in ComputerController is not implemented yet", exception);
        }
        return responseEntity("Computer was successfully deleted");
    }

}
