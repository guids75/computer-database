package com.excilys.formation.computerdatabase.dto;

import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

/**
 * @author GUIDS
 *
 */
public class ComputerDto {

  private int id; //id of the computer, required
  private String name; //name of the computer, required
  private String introduced; //date when the computer was introduced, optional
  private String discontinued; //date when the computer was discontinued, optional
  private int companyId; //id of the company which produces the computer, required
  private String companyName; //name of the company which produces the computer, required
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getIntroduced() {
    return introduced;
  }
  
  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }
  
  public String getDiscontinued() {
    return discontinued;
  }
  
  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }
  
  public int getCompanyId() {
    return companyId;
  }

  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }
  
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }



  @Override
  public String toString() {
    return new StringBuilder("Computer [id=").append(id).append(", name=").append(name)
        .append(", introduced=").append(introduced).append(", discontinued=").append(discontinued)
        .append(", companyId=").append(companyId).append(", companyName=").append(companyName)
        .append("]").toString();
  }

}
