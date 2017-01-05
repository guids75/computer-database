package com.excilys.formation.computerdatabase.ui.company;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

/**
 * @author GUIDS
 *
 */
@Controller
public class CompanyUiImpl implements CompanyUi {

    @Autowired
    private CompanyService companyService; // service of Company to manage them
    private Page pages; // pages' attributes to manage them
    private int offset = 0;

    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);
    
    /**
     * Create a new instance of Page and set their number according to the number
     * of companies.
     * 
     */
    public CompanyUiImpl(CompanyService companyService) {
        this.companyService = companyService;
        pages = new Page(companyService.count());
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public void list() {
        print(CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                .limit(pages.getNbElementsByPage()).offset(offset).build())));
        System.out.println("Type b to see before, a to see after, q to quit");
        String line = scanner.nextLine();
        while (!line.equals("q")) {
            if (line.equals("a")) {
                if (pages.hasNext()) {
                    offset += pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() + 1);
                print(CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                        .limit(pages.getNbElementsByPage()).offset(offset).build())));
                line = scanner.nextLine();
            }
            if (line.equals("b")) {
                if (pages.hasPrev()) {
                    offset -= pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() - 1);
                print(CompanyDtoMapper.companyListToCompanyDtoList(companyService.list(new Constraints.ConstraintsBuilder()
                        .limit(pages.getNbElementsByPage()).offset(offset).build())));
                line = scanner.nextLine();
            }
        }
    }

    @Override
    public void delete() {
        System.out.println("which company id?");
        try {
            companyService.delete(new Constraints.ConstraintsBuilder().idCompany(scanner.nextLong()).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in companyService is not implemented yet", exception);
        }
        scanner.nextLine();
        pages.setNbPages(pages.getNbPages() - 1);
    }

    @Override
    public void print(List<CompanyDto> companies) {
        companies.forEach(company-> System.out.println(company));
    }

}
