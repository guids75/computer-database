package com.excilys.formation.computerdatabase.ui.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.dto.CompanyDto;

import com.excilys.formation.computerdatabase.mapper.CompanyDtoMapper;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;

/**
 * @author GUIDS
 *
 */
public class CompanyUiImpl implements CompanyUi {

    @Autowired
    private CompanyServiceImpl companyService; // service of Company to manage them
    private Page pages; // pages' attributes to manage them
    private int offset = 0;

    /**
     * Create a new instance of Page and set their number according to the number
     * of companies.
     * 
     */
    public CompanyUiImpl(CompanyServiceImpl companyService) {
        this.companyService = companyService;
        pages = new Page(companyService.count());
    }

    public CompanyServiceImpl getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyServiceImpl companyService) {
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
     /*   companyService.delete(new Constraints.ConstraintsBuilder().idCompany(scanner.nextLong()).build());
        scanner.nextLine();
        pages.setNbPages(pages.getNbPages() - 1);*/
    }

    /**
     * Print the list of companies.
     * 
     * @param companies
     *          : list of companies to print
     */
    public void print(List<CompanyDto> companies) {
        companies.forEach(company-> System.out.println(company));
    }

}
