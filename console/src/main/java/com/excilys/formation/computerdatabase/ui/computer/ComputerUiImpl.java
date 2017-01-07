package com.excilys.formation.computerdatabase.ui.computer;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto.ComputerDtoBuilder;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.constraints.Constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.excilys.formation.computerdatabase.ui.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

/**
 * @author GUIDS
 *
 */
@Controller
public class ComputerUiImpl implements ComputerUi {

    private ComputerDtoBuilder computer;
    @Autowired
    private DatabaseService databaseService;

    private String intro = "";
    private String disco = "";
    private Long companyId;
    private int nbComputers;

    private int offset = 0;
    private Page pages; // pages' attributes to manage them

    private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);

    /**
     * Create a new instance of Page and set their number according to the number
     * of computers.
     *
     */
    public ComputerUiImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
        nbComputers = databaseService.count(new Constraints.ConstraintsBuilder().build());
        pages = new Page(nbComputers);
    }


    @Override
    public void list() {
        Locale locale = LocaleContextHolder.getLocale();
        print(databaseService.listComputers(new Constraints.ConstraintsBuilder()
                .page(pages).limit(pages.getNbElementsByPage()).offset(offset).build()));
        System.out.println("Type b to see before, a to see after, q to quit");
        String line = scanner.nextLine();
        while (!line.equals("q")) {
            if (line.equals("a")) {
                if (pages.hasNext()) {
                    offset += pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() + 1);
                print(databaseService.listComputers(new Constraints.ConstraintsBuilder()
                        .page(pages).limit(pages.getNbElementsByPage()).offset(offset).build()));
                line = scanner.nextLine();
            }
            if (line.equals("b")) {
                if (pages.hasPrev()) {
                    offset -= pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() - 1);
                print(databaseService.listComputers(new Constraints.ConstraintsBuilder()
                        .page(pages).limit(pages.getNbElementsByPage()).offset(offset).build()));
                line = scanner.nextLine();
            }
        }
    }

    /**
     * Print a list of computers.
     *
     * @param computers
     *          : list of computers to print.
     */
    public void print(List<ComputerDto> computers) {
        computers.forEach(computer-> System.out.println(computer));
    }

    @Override
    public void showComputerDetails() {
        System.out.println("which id?");
        while (!scanner.hasNextInt()) {
            System.out.println("You must use an integer");
            scanner.next();
        }
        System.out.println(databaseService.showComputerDetails(scanner.nextLong()));
        scanner.nextLine();
    }

    @Override
    public void insert() {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("which name?");
        String name = scanner.nextLine();
        System.out.println("which introducing date? (yyyy-M-dd)");
        intro = scanner.nextLine();

        System.out.println("which discontinuing date? (yyyy-M-dd)");
        disco = scanner.nextLine();

        System.out.println("which company id?");
        companyId = scanner.nextLong();
        scanner.nextLine();
        computer = new ComputerDto.ComputerDtoBuilder(name);
        if (intro != null) {
            computer.introduced(intro);
        }
        if (disco != null) {
            computer.discontinued(disco);
        }
        if (companyId != null) {
            computer.companyId(companyId);
        }
        databaseService.insert(ComputerDtoMapper.computerDtoToComputer(computer.build(), locale));
        pages.setNbPages(pages.getNbPages() + 1);
    }

    @Override
    public void update() {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("which computer id?");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("which name?");
        String name = scanner.nextLine();
        System.out.println("which introducing date? (yyyy-M-dd)");
        intro = scanner.nextLine();

        System.out.println("which discontinuing date? (yyyy-M-dd)");
        disco = scanner.nextLine();

        System.out.println("which company id?");
        companyId = scanner.nextLong();
        scanner.nextLine();

        computer = new ComputerDto.ComputerDtoBuilder(name);
        computer.id(id);
        if (intro != null) {
            computer.introduced(intro);
        }
        if (disco != null) {
            computer.discontinued(disco);
        }
        computer.companyId(companyId);
        databaseService.update(ComputerDtoMapper.computerDtoToComputer(computer.build(), locale));
    }

    @Override
    public void delete() {
        System.out.println("which computer id?");
        ArrayList<Long> idList = new ArrayList<Long>();
        idList.add(scanner.nextLong());
        databaseService.deleteComputer(new Constraints.ConstraintsBuilder().idList(idList).build());
        scanner.nextLine();
        pages.setNbPages(pages.getNbPages() - 1);
    }

}
