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
  private LocalDate introduced; //date when the computer was introduced, optional
  private LocalDate discontinued; //date when the computer was discontinued, optional
  private Company company; //company which produces the computer, required

  /**
   * @return the current id of the computer
   */
  public int getId() {
    return id;
  }

  /**
   * @return the current name of the computer
   */
  public String getName() {
    return name;
  }

  /**
   * @return the current date when the computer was introduced
   */
  public LocalDate getIntroduced() {
    return introduced;
  }

  /**
   * @return the current date when the computer was discontinued
   */
  public LocalDate getDiscontinued() {
    return discontinued;
  }

  /**
   * @return the computer which produces the computer
   */
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
