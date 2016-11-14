package com.excilys.formation.computerdatabase.ui;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.persistence.CompanyDao;
import com.excilys.formation.computerdatabase.service.CompanyService;

public class CompanyUi implements CompanyUiInterface {

  private CompanyService companyService = CompanyService.getInstance();
  private int offset = 0;

  public void list() {
    Page<Company> pages = new Page<Company>(companyService.list(-1, offset, false).size());
    companyService.list(Page.getNbElementsByPage(), offset, true);
    System.out.println("Type b to see before, a to see after, q to quit");
    String s = sc.nextLine();
    while (!s.equals("q")){
      if (s.equals("a")){
        if (pages.hasNext()) {
          offset += Page.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage()+1);
        companyService.list(Page.getNbElementsByPage(), offset, true);
        System.out.println(offset + "here");
        s = sc.nextLine();
      }
      if (s.equals("b")){
        if (pages.hasPrev()) {
          offset -= Page.getNbElementsByPage();
        }
        pages.setActualPage(pages.getActualPage()-1);
        companyService.list(Page.getNbElementsByPage(), offset, true);
        s = sc.nextLine();
      }
    }
  }

}
