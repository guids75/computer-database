package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto.ComputerDtoBuilder;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.Constraints.ConstraintsBuilder;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

public final class RequestMapper {

    private static final ComputerService computerService = ComputerServiceImpl.INSTANCE;

    private RequestMapper() {
    }

    public static ComputerDto convertToComputer(HttpServletRequest request) {
        if (request.getParameter("computerName") != null) {
            ComputerDtoBuilder computerDto = new ComputerDto.ComputerDtoBuilder(request.getParameter("computerName"));
            if (request.getParameter("id") != null) {
                computerDto.id(Long.parseLong(request.getParameter("id")));
            }
            if (request.getParameter("introduced") != "") {
                computerDto.introduced(request.getParameter("introduced"));
            }
            if (request.getParameter("discontinued") != "") {
                computerDto.discontinued(request.getParameter("discontinued"));
            }
            if (request.getParameter("companyId") != null && !request.getParameter("companyId").equals("")) {
                computerDto.companyId(Long.parseLong(request.getParameter("companyId")));
            } else {
                computerDto.companyId(0L);
            }
            return computerDto.build();
        }
        return null;
    }

    public static List<Long> convertToComputersId(HttpServletRequest request) {
        List<Long> computersId = new ArrayList<>();
        String selection = request.getParameter("selection");
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

    public static Page convertToPage(HttpServletRequest request) {
        Page pages = new Page();
        // if there is an actual page, use it, otherwise use the first page
        if (request.getParameter("actualPage") != null) {
            pages.setActualPage(Integer.parseInt(request.getParameter("actualPage")));
        } else {
            pages.setActualPage(1);
        }
        // if there is a nbElementsByPage defined, use it, otherwise use 10
        if (request.getParameter("nbElementsByPage") != null) {
            pages.setNbElementsByPage(Integer.parseInt(request.getParameter("nbElementsByPage")));
        } else {
            pages.setNbElementsByPage(10);
        }
        // get the right number of pages according to the search
        if (request.getParameter("search") != null) {
            pages.setNbElements(computerService
                    .count(new Constraints.ConstraintsBuilder().search(request.getParameter("search")).build()));
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
    
    public static ConstraintsBuilder convertToConstraints(HttpServletRequest request) {
        ConstraintsBuilder constraints = new Constraints.ConstraintsBuilder();
        if (request.getParameter("order") != null) {
            constraints.orderBy(request.getParameter("order").replace('_', '.'));
        }
        if (request.getParameter("search") != null) {
            constraints.search(request.getParameter("search"));
        }
        return constraints;
    }

}
