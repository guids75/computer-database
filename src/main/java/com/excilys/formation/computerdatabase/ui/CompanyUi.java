package com.excilys.formation.computerdatabase.ui;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.persistence.CompanyDao;
import com.excilys.formation.computerdatabase.service.CompanyService;

public class CompanyUi implements CompanyUiInterface {

  private CompanyService companyService = CompanyService.getInstance();
  private Page<Company> pages;
  private int offset = 0;

  public CompanyUi() {
    pages = new Page<>(companyService.getNumber());
  }

  @Override
  public void list() {
    print(companyService.list(Page.getNbElementsByPage(), offset));
    System.out.println("Type b to see before, a to see after, q to quit");
    String line = scanner.nextLine();
    while (!line.equals("q")) {
      if (line.equals("a")) {
        if (pages.hasNext()) {
          offset += Page.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage() + 1);
        print(companyService.list(Page.getNbElementsByPage(), offset));
        line = scanner.nextLine();
      }
      if (line.equals("b")) {
        if (pages.hasPrev()) {
          offset -= Page.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage() - 1);
        print(companyService.list(Page.getNbElementsByPage(), offset));
        line = scanner.nextLine();
      }
    }
  }

  public void print(List<Company> companies){
    for (Company company : companies){
      System.out.println(company);
    }
  }

}
