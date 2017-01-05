package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto.ComputerDtoBuilder;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.constraints.Constraints.ConstraintsBuilder;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;

public final class RequestParamMapper {

    private ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context-binding.xml"); 
    private ComputerService computerService = (ComputerService) appContext.getBean(ComputerService.class);

    
    public ComputerService getComputerService() {
        return computerService;
    }

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    
    public static ComputerDto convertToComputer(Map<String, String> parameters) {
        if (parameters.get("computerName") != null) {
            ComputerDtoBuilder computerDto = new ComputerDto.ComputerDtoBuilder(parameters.get("computerName"));
            if (parameters.get("id") != null) {
                computerDto.id(Long.parseLong(parameters.get("id")));
            }
            if (parameters.get("introduced") != "") {
                computerDto.introduced(parameters.get("introduced"));
            }
            if (parameters.get("discontinued") != "") {
                computerDto.discontinued(parameters.get("discontinued"));
            }
            if (parameters.get("companyId") != null && !parameters.get("companyId").equals("")) {
                computerDto.companyId(Long.parseLong(parameters.get("companyId")));
            } else {
                computerDto.companyId(0L);
            }
            return computerDto.build();
        }
        return null;
    }

    public static List<Long> convertToComputersId(Map<String, String> parameters) {
        List<Long> computersId = new ArrayList<>();
        String selection = parameters.get("selection");
        String[] deletedComputers = selection.split(",");
        if (deletedComputers.length > 0) {
            for (int i = 0; i < deletedComputers.length; i++) {
                Long id = Long.parseLong(deletedComputers[i]);
                if (id > 0) {
                    computersId.add(id);
                } else {
                    throw new IllegalArgumentException("An id is not consistent when deleting the computers");
                }
            }
        }
        return computersId;
    }

    public Page convertToPage(Map<String, String> parameters) {
        System.out.println("problem");
        Page pages = new Page();
        // if there is an actual page, use it, otherwise use the first page
        if (parameters.get("actualPage") != null) {
            pages.setActualPage(Integer.parseInt(parameters.get("actualPage")));
        } else {
            pages.setActualPage(1);
        }
        // if there is a nbElementsByPage defined, use it, otherwise use 10
        if (parameters.get("nbElementsByPage") != null) {
            pages.setNbElementsByPage(Integer.parseInt(parameters.get("nbElementsByPage")));
        } else {
            pages.setNbElementsByPage(10);
        }
        // get the right number of pages according to the search
        if (parameters.get("search") != null) {
            pages.setNbElements(computerService
                    .count(new Constraints.ConstraintsBuilder().search(parameters.get("search")).build()));
        } else {
            pages.setNbElements(computerService.count(new Constraints.ConstraintsBuilder().search("").build()));
        }
        pages.calculateNbPages(pages.getNbElements());

        // when it's the last page
        if (pages.getActualPage() - 1 * pages.getNbElementsByPage() >= pages.getNbElements()) {
            pages.setActualPage(pages.getActualPage() - 1);
        }

        return pages;
    }
    
    public static ConstraintsBuilder convertToConstraints(Map<String, String> parameters) {
        ConstraintsBuilder constraints = new Constraints.ConstraintsBuilder();
        if (parameters.get("order") != null) {
            constraints.orderBy(parameters.get("order").replace('_', '.'));
        }
        if (parameters.get("search") != null) {
            constraints.search(parameters.get("search"));
        }
        return constraints;
    }

}
