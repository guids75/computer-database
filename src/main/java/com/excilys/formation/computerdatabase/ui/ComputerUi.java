package com.excilys.formation.computerdatabase.ui;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Page;
import com.excilys.formation.computerdatabase.persistence.ComputerDao;
import com.excilys.formation.computerdatabase.service.ComputerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ComputerUi implements ComputerUiInterface {

  private Computer computer;
  private ComputerService computerService = ComputerService.getInstance();
  private String intro = "";
  private String disco = "";
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd"); 
  private int company_id;

  private int offset = 0;


  public void list(Page p){

    computerService.list(p.getNb_elements_page(), offset);
    System.out.println("Type b to see before, a to see after, q to quit");
    String s = sc.nextLine();
    while (!s.equals("q")){
      if (s.equals("a")){
        //if (offset + p.getNb_elements_page() < p.getNb_elements_total()) 
        offset += p.getNb_elements_page();
        computerService.list(p.getNb_elements_page(), offset);
        System.out.println(offset + "here");
        s = sc.nextLine();
      }
      if (s.equals("b")){
        if (offset >= p.getNb_elements_page()) offset -= p.getNb_elements_page();
        computerService.list(p.getNb_elements_page(), offset);
        s = sc.nextLine();
      }
    }
  }

  public void showComputerDetails(){
    System.out.println("which id?");
    while (!sc.hasNextInt()) {
      System.out.println("You must use an integer");
      sc.next();
    }
    computerService.showComputerDetails(sc.nextInt());
    sc.nextLine();
  }

  public void insert(Page p){
    /*System.out.println("which name?");
    c.setName(sc.nextLine());
    System.out.println("which introducing date? (yyyy-M-dd)");
    intro = sc.nextLine();
    if (!intro.equals("")){
      try {
        c.setIntroduced(sdf.parse(intro));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("which discontinuing date? (yyyy-M-dd)");
    disco = sc.nextLine();
    if (!disco.equals("")){
      try {
        c.setIntroduced(sdf.parse(disco));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("which company id?");
    company_id = sc.nextInt();
    if (company_id != 0) c.setCompany_id(company_id);
    sc.nextLine();
    gcp.insertComputer(c);
    listComputers(p);*/
  }

  public void update(Page p){
    System.out.println("which computer id?");
    c.setId(sc.nextInt());
    sc.nextLine();
    System.out.println("which name?");
    c.setName(sc.nextLine());
    System.out.println("which introducing date? (yyyy-M-dd)");
    intro = sc.nextLine();
    if (!intro.equals("")) {
      try {
        c.setIntroduced(sdf.parse(intro));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("which discontinuing date? (yyyy-M-dd)");
    disco = sc.nextLine();
    if (!disco.equals("")) {
      try {
        c.setIntroduced(sdf.parse(disco));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("which company id?");
    company_id = sc.nextInt();
    if (company_id != 0) c.setCompany_id(company_id);
    sc.nextLine();
    gcp.updateComputer(c);
    listComputers(p);
  }

  public void delete(Page p){
    System.out.println("which computer id?");
    computerService.delete(sc.nextInt());
    sc.nextLine();
    list(p);
  }

}
