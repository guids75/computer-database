package com.excilys.formation.computerdatabase.ui.company;

import java.util.List;

import com.excilys.formation.computerdatabase.ui.service.DatabaseService;
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
    private DatabaseService databaseService; // service of Company to manage them
    private Page pages; // pages' attributes to manage them
    private int offset = 0;

    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);

    /**
     * Create a new instance of Page and set their number according to the number
     * of companies.
     *
     */
    public CompanyUiImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
        pages = new Page(databaseService.count(new Constraints.ConstraintsBuilder().build()));
    }


    @Override
    public void list() {
        print(databaseService.listCompanies(new Constraints.ConstraintsBuilder()
                .page(pages).limit(pages.getNbElementsByPage()).offset(offset).build()));
        System.out.println("Type b to see before, a to see after, q to quit");
        String line = scanner.nextLine();
        while (!line.equals("q")) {
            if (line.equals("a")) {
                if (pages.hasNext()) {
                    offset += pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() + 1);
                print(databaseService.listCompanies(new Constraints.ConstraintsBuilder()
                        .limit(pages.getNbElementsByPage()).offset(offset).build()));
                line = scanner.nextLine();
            }
            if (line.equals("b")) {
                if (pages.hasPrev()) {
                    offset -= pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() - 1);
                print(databaseService.listCompanies(new Constraints.ConstraintsBuilder()
                        .limit(pages.getNbElementsByPage()).offset(offset).build()));
                line = scanner.nextLine();
            }
        }
    }

    @Override
    public void delete() {
        System.out.println("which company id?");
        databaseService.deleteCompany(new Constraints.ConstraintsBuilder().idCompany(scanner.nextLong()).build());
        scanner.nextLine();
        pages.setNbPages(pages.getNbPages() - 1);
    }

    @Override
    public void print(List<CompanyDto> companies) {
        companies.forEach(company-> System.out.println(company));
    }

}
