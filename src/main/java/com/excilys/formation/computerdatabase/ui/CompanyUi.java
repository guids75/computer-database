package com.excilys.formation.computerdatabase.ui;

import java.util.List;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.persistence.CompanyDao;
import com.excilys.formation.computerdatabase.service.CompanyService;

/**
 * @author GUIDS
 *
 */
public class CompanyUi implements CompanyUiInterface {

  private CompanyService companyService = CompanyService.getInstance(); //service of Company to manage them
  private Page<Company> pages; //pages' attributes to manage them
  private int offset = 0;

  /** Create a new instance of Page and set their number according to the number of companies.
   * 
   */
  public CompanyUi() throws ConnectionException {
    pages = new Page<>(companyService.getNumber());
  }

  @Override
  public void list() throws ConnectionException {
    print(companyService.list(pages.getNbElementsByPage(), offset));
    System.out.println("Type b to see before, a to see after, q to quit");
    String line = scanner.nextLine();
    while (!line.equals("q")) {
      if (line.equals("a")) {
        if (pages.hasNext()) {
          offset += pages.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage() + 1);
        print(companyService.list(pages.getNbElementsByPage(), offset));
        line = scanner.nextLine();
      }
      if (line.equals("b")) {
        if (pages.hasPrev()) {
          offset -= pages.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage() - 1);
        print(companyService.list(pages.getNbElementsByPage(), offset));
        line = scanner.nextLine();
      }
    }
  }

  /** Print the list of companies.
   * 
   * @param companies : list of companies to print
   */
  public void print(List<Company> companies) {
    for (Company company : companies) {
      System.out.println(company);
    }
  }

}
