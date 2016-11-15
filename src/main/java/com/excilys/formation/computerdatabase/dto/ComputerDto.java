package com.excilys.formation.computerdatabase.dto;

import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

public class ComputerDto {

  private int id; //required
  private String name; //required
  private LocalDate introduced; //optional
  private LocalDate discontinued; //optional
  private Company company; //required

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getIntroduced() {
    return introduced;
  }

  public LocalDate getDiscontinued() {
    return discontinued;
  }

  public Company getCompany() {
    return company;
  }

  /*@Override
  public String toString() {
    return new StringBuilder("Computer [id=").append(id).append(", name=").append(name)
        .append(", introduced=").append(introduced).append(", discontinued=").append(discontinued)
        .append(", company=").append(company).append("]").toString();
  }*/
  
}
