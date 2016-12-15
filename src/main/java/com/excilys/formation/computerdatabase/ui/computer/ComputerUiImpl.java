package com.excilys.formation.computerdatabase.ui.computer;

import com.excilys.formation.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto.ComputerDtoBuilder;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.exception.NotImplementedMethodException;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.service.company.CompanyService;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.computer.ComputerService;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author GUIDS
 *
 */
@Controller
public class ComputerUiImpl implements ComputerUi {

    private ComputerDtoBuilder computer;
    @Autowired
    private ComputerService computerService; // service of Computer to manage them
    @Autowired
    private CompanyService companyService; // service of Company to manage them

    private String intro = "";
    private String disco = "";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
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
    public ComputerUiImpl(ComputerService computerService) {
        this.computerService = computerService;
        nbComputers = computerService.count(new Constraints.ConstraintsBuilder().search("").build());
        pages = new Page(nbComputers);
    }

    
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


    @Override
    public void list() {
        print(ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                .limit(pages.getNbElementsByPage()).offset(offset).build())));
        System.out.println("Type b to see before, a to see after, q to quit");
        String line = scanner.nextLine();
        while (!line.equals("q")) {
            if (line.equals("a")) {
                if (pages.hasNext()) {
                    offset += pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() + 1);
                print(ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                        .limit(pages.getNbElementsByPage()).offset(offset).build())));
                line = scanner.nextLine();
            }
            if (line.equals("b")) {
                if (pages.hasPrev()) {
                    offset -= pages.getNbElementsByPage();
                }
                pages.setActualPage(pages.getActualPage() - 1);
                print(ComputerDtoMapper.computerListToComputerDtoList(computerService.list(new Constraints.ConstraintsBuilder()
                        .limit(pages.getNbElementsByPage()).offset(offset).build())));
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
        computerService.showComputerDetails(scanner.nextLong());
        scanner.nextLine();
    }

    @Override
    public void insert() {
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
        try {
            computerService.insert(ComputerDtoMapper.computerDtoToComputer(computer.build()));
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("insert in computerService is not implemented yet", exception);
        }
        pages.setNbPages(pages.getNbPages() + 1);
    }

    @Override
    public void update() {
        System.out.println("which computer id?");
        long id = scanner.nextLong();
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
        if (intro != null) {
            computer.introduced(intro);
        }
        if (disco != null) {
            computer.discontinued(disco);
        }
        computer.companyId(companyId);
        try {
            computerService.update(ComputerDtoMapper.computerDtoToComputer(computer.build()));
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("update in computerService is not implemented yet", exception);
        }
    }

    @Override
    public void delete() {
        System.out.println("which computer id?");
        ArrayList<Long> idList = new ArrayList<Long>();
        idList.add(scanner.nextLong());
        try {
            computerService.delete(new Constraints.ConstraintsBuilder().idList(idList).build());
        } catch (NotImplementedMethodException exception) {
            slf4jLogger.error("delete in computerService is not implemented yet", exception);
        }
        scanner.nextLine();
        pages.setNbPages(pages.getNbPages() - 1);
    }

}
