package com.excilys.formation.computerdatabase.test;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.persistence.JdbcConnection;

public class MainTest {

  private static final JdbcConnection jdbcConnection = JdbcConnection.getInstance();

  /**
   * 
   * @param args
   */
  public static void main(String[] args) throws ConnectionException {


    jdbcConnection.openConnection();
    jdbcConnection.closeConnection();
    /*GestionCompany gcn = new GestionCompany();
    List<Company> companies = gcn.listCompanies();
    Company c = new Company();
    c.setId(((Company) companies.get(companies.size()-1)).getId()+1);
    c.setName("Company2");
    //gcn.insertCompany(c);
    System.out.println("--------------------------");
    gcn.listCompanies();

    GestionComputer gcp = new GestionComputer();
    List<Computer> computers = gcp.listComputers();
    gcp.showComputerDetails(12);
    Computer cp = new Computer();
    cp.setId(computers.get(computers.size()-1).getId()+1);
    cp.setName("cpt1");
    cp.setCompany_id(1);
    gcp.insertComputer(cp);
    gcp.listComputers();
    Computer cp2 = new Computer();
    cp2.setId(cp.getId());
    cp2.setName("cpt2");
    cp2.setCompany_id(2);
    gcp.updateComputer(cp2);
    gcp.listComputers();
    gcp.deleteComputer(575);*/


    //gcp.deleteComputer(581);

    /*gcp.deleteComputer(582);
    gcp.listComputers();*/

  }

}
