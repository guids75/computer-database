package com.excilys.formation.computerdatabase.ui.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.pagination.Page;
import com.excilys.formation.computerdatabase.service.company.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.computer.ComputerServiceImpl;

import java.time.ZoneId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * @author GUIDS
 *
 */
public class ComputerUiImpl implements ComputerUi {

  private ComputerDto computer;
  private static final ComputerServiceImpl computerService = ComputerServiceImpl.getInstance(); //service of Computer to manage them
  private static final CompanyServiceImpl companyService = CompanyServiceImpl.getInstance(); //service of COmpany to manage them

  private String intro = "";
  private String disco = "";
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd"); 
  private int companyId;
  private int nbComputers;

  private int offset = 0;
  private Page<ComputerDto> pages; //pages' attributes to manage them

  /** Create a new instance of Page and set their number according to the number of computers.
   * 
   */
  public ComputerUiImpl() {
    nbComputers = computerService.count();
    pages = new Page<>(nbComputers);
  }

  @Override
  public void list() {
    print(computerService.list(pages.getNbElementsByPage(), offset));
    System.out.println("Type b to see before, a to see after, q to quit");
    String line = scanner.nextLine();
    while (!line.equals("q")) {
      if (line.equals("a")) {
        if (pages.hasNext()) {
          offset += pages.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage() + 1);
        print(computerService.list(pages.getNbElementsByPage(), offset));
        line = scanner.nextLine();
      }
      if (line.equals("b")) {
        if (pages.hasPrev()) {
          offset -= pages.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage() - 1);
        print(computerService.list(pages.getNbElementsByPage(), offset));
        line = scanner.nextLine();
      }
    }
  }

  /** Print a list of computers.
   * 
   * @param computers : list of computers to print.
   */
  public void print(List<ComputerDto> computers) {
    for (ComputerDto computer : computers) {
      System.out.println(computer);
    }
  }

  @Override
  public void showComputerDetails() {
    System.out.println("which id?");
    while (!scanner.hasNextInt()) {
      System.out.println("You must use an integer");
      scanner.next();
    }
    computerService.showComputerDetails(scanner.nextInt());
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
    companyId = scanner.nextInt();
    scanner.nextLine();
    computer = new ComputerDto();
    computer.setName(name);
    if (intro != null) {
      computer.setIntroduced(intro);
    }
    if (disco != null) {
      computer.setDiscontinued(disco);
    }
    computer.setCompanyId(companyId);
    computerService.insert(computer);
    pages.setNbPages(pages.getNbPages() + 1);
  }

  @Override
  public void update() {
    System.out.println("which computer id?");
    int id = scanner.nextInt();
    scanner.nextLine();
    System.out.println("which name?");
    String name = scanner.nextLine();
    System.out.println("which introducing date? (yyyy-M-dd)");
    intro = scanner.nextLine();

    System.out.println("which discontinuing date? (yyyy-M-dd)");
    disco = scanner.nextLine();

    System.out.println("which company id?");
    companyId = scanner.nextInt();
    scanner.nextLine();

    computer = new ComputerDto();
    computer.setName(name);
    if (intro != null) {
      computer.setIntroduced(intro);
    }
    if (disco != null) {
      computer.setDiscontinued(disco);
    }
    computer.setCompanyId(companyId);
    computerService.update(computer);
  }

  @Override
  public void delete() {
    System.out.println("which computer id?");
    computerService.delete(scanner.nextInt());
    scanner.nextLine();
    pages.setNbPages(pages.getNbPages() - 1);
  }

}
